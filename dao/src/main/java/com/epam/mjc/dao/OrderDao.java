package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.OrderEntity;

import java.math.BigInteger;
import java.util.List;

public interface OrderDao {
    OrderEntity getOrderById(Long id);
    Long createOrder(OrderEntity orderEntity);
    List<OrderEntity> getAllByUserId(Long userId, Integer size, Integer pageNumber);
    BigInteger ordersSize(Long userId);

}
