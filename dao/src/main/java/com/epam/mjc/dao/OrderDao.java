package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Long createOrder(Order order);
    List<Order> getAllByUserId(Long userId);
}
