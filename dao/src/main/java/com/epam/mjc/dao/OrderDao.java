package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Optional<OrderEntity> getOrderById(Long id);
    Long createOrder(OrderEntity orderEntity);
    List<OrderEntity> getAllByUserId(Long userId, Integer size, Integer pageNumber);
    int ordersSize(Long userId);

}
