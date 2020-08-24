package com.epam.mjc.service.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.exception.DataAccessException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.mapper.GiftCertificateMapper;
import com.epam.mjc.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final GiftCertificateDao certificateDao;
    private final UserDao userDao;
    private final OrderMapper mapper;
    private final GiftCertificateMapper certificateMapper;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, GiftCertificateDao certificateDao, UserDao userDao, OrderMapper mapper, GiftCertificateMapper certificateMapper) {
        this.orderDao = orderDao;
        this.certificateDao = certificateDao;
        this.userDao = userDao;
        this.mapper = mapper;
        this.certificateMapper = certificateMapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Long certificateId = orderDto.getCertificateId();
        GiftCertificateEntity certificate = certificateDao.getById(certificateId)
                .orElseThrow(()-> new NotFoundException("Certificate with id "+ certificateId + " not found"));
        Long userId = orderDto.getUserId();
        userDao.getUserById(userId).orElseThrow(()-> new NotFoundException("User not found with given user id"));
        BigDecimal certificatePrice = certificate.getPrice();
        orderDto.setAmount(certificatePrice);
        orderDto.setDate(LocalDateTime.now());
        orderDto.setCertificateId(certificateId);
        Long id = orderDao.createOrder(mapper.toEntity(orderDto));
        return orderDao.getOrderById(id).map( mapper::toDto).orElse(null);
    }

    @Override
    public OrderDto getOrderById(Long id, Long userId) {
        OrderDto order = orderDao.getOrderById(id).map( mapper::toDto).orElseThrow(() -> new NotFoundException("Order with such id not found"));
        if(userId != null && !order.getUserId().equals(userId)) {
            throw new DataAccessException("Impossible to get order by id " + id);
        }
        GiftCertificateDto giftCertificateDto = certificateDao.getById(order.getCertificateId()).map(certificateMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Certificate for order not found"));
        order.setCertificate(giftCertificateDto);
        return order;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId, Integer size, Integer pageNumber) {
        return orderDao.getAllByUserId(userId, size, pageNumber).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public int countOrders(Long userId) {
        return orderDao.ordersSize(userId);
    }
}
