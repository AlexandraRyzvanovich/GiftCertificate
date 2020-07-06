package com.epam.mjc.service;

import com.epam.mjc.dao.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getOrders();
}
