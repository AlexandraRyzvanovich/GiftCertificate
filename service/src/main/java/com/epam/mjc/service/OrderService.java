package com.epam.mjc.service;

import com.epam.mjc.dao.dto.OrderDto;

import java.math.BigInteger;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id);
    List<OrderDto> getOrdersByUserId(Long userId, Integer size, Integer pageNumber);
    BigInteger countOrders(Long userId);
}
