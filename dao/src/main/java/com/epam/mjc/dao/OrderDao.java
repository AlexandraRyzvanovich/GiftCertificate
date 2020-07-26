package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.OrderEntity;

import java.util.List;

public interface OrderDao {
    OrderEntity getOrderById(Long id);
    Long createOrder(OrderEntity orderEntity);
    List<OrderEntity> getAllByUserId(Long userId, Integer size, Integer pageNumber);
    int ordersSize(Long userId);

}
