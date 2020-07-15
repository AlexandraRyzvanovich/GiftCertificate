package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.OrderEntity;

import java.util.List;

public interface OrderDao {
    List<OrderEntity> getAllOrders();
    OrderEntity getOrderById(Long id);
    Long createOrder(OrderEntity orderEntity);
    List<OrderEntity> getAllByUserId(Long userId);
}
