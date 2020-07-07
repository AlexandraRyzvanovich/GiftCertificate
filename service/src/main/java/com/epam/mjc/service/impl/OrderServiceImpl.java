package com.epam.mjc.service.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.Order;
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
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GiftCertificateDao certificateDao;
    @Autowired
    private UserDao userDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order createOrder(Order order) {
        Long certificateId = order.getCertificateId();
        GiftCertificate certificate = certificateDao.getById(certificateId);
        Long userId = order.getUserId();
        User user = userDao.getUserById(userId);
        if(certificate == null && user == null) {
            throw new IncorrectParamsException("Impossible to create order with given data. Certificate id or user id is incorrect");
        }
        Validator.validateOrder(order);
        BigDecimal certificatePrice = certificate.getPrice();
        order.setAmount(certificatePrice);
        Long id = orderDao.createOrder(order);
        return orderDao.getOrderById(id);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getAllOrders();
    }
}
