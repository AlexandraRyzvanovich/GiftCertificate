package com.epam.mjc.service;

import com.epam.mjc.dao.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderEntity orderEntity);
    OrderEntity getOrderById(Long id);
    List<OrderEntity> getOrders();
}
