package com.epam.mjc.service;

import com.epam.mjc.dao.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id, Long userId);
    List<OrderDto> getOrdersByUserId(Long userId, Integer size, Integer pageNumber);
    int countOrders(Long userId);
}
