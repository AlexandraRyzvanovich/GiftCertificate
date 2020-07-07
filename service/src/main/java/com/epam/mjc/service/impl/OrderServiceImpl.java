package com.epam.mjc.service.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.entity.Order;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.validator.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    @Override
    public Order createOrder(Order order) {
        Validator.validateOrder(order);
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
