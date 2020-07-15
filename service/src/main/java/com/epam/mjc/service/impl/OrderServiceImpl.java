package com.epam.mjc.service.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.dao.entity.OrderEntity;
import com.epam.mjc.dao.entity.User;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.util.List;

@Service
@EnableTransactionManagement
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final GiftCertificateDao certificateDao;
    private final UserDao userDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, GiftCertificateDao certificateDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.certificateDao = certificateDao;
        this.userDao = userDao;
    }

    @Override
    public OrderEntity createOrder(OrderEntity orderEntity) {
        Long certificateId = orderEntity.getCertificateId();
        GiftCertificateEntity certificate = certificateDao.getById(certificateId);
        Long userId = orderEntity.getUserId();
        User user = userDao.getUserById(userId);
        if(certificate == null && user == null) {
            throw new IncorrectParamsException("Impossible to create order with given data. Certificate id or user id is incorrect");
        }
        Validator.validateOrder(orderEntity);
        BigDecimal certificatePrice = certificate.getPrice();
        orderEntity.setAmount(certificatePrice);
        Long id = orderDao.createOrder(orderEntity);
        return orderDao.getOrderById(id);
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<OrderEntity> getOrders() {
        return orderDao.getAllOrders();
    }
}
