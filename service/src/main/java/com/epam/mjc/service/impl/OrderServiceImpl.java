package com.epam.mjc.service.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.dao.entity.UserEntity;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.exception.IncorrectParamsException;
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
@EnableTransactionManagement
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final GiftCertificateDao certificateDao;
    private final UserDao userDao;
    private final OrderMapper mapper;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, GiftCertificateDao certificateDao, UserDao userDao, OrderMapper mapper) {
        this.orderDao = orderDao;
        this.certificateDao = certificateDao;
        this.userDao = userDao;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Long certificateId = orderDto.getCertificateId();
        GiftCertificateEntity certificate = certificateDao.getById(certificateId);
        Long userId = orderDto.getUserId();
        UserEntity userEntity = userDao.getUserById(userId);
        if(userEntity == null) {
            throw new IncorrectParamsException("User not found with given user id");
        }
        BigDecimal certificatePrice = certificate.getPrice();
        orderDto.setAmount(certificatePrice);
        orderDto.setDate(LocalDateTime.now());
        //orderDto.setCertificateId(certificateId);
        Long id = orderDao.createOrder(mapper.toEntity(orderDto));
        return mapper.toDto(orderDao.getOrderById(id));
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return mapper.toDto(orderDao.getOrderById(id));
    }

    @Override
    public List<OrderDto> getOrders() {
        return orderDao.getAllOrders().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return orderDao.getAllByUserId(userId).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
