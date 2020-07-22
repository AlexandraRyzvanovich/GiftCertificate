package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #userId")
    public OrderDto getOrderById(@PathVariable("id") Long id) {

        return orderService.getOrderById(id);
    }

    @PostMapping()
    @PreAuthorize("authentication.principal.id == #userId")
    public OrderDto createOrder(@RequestBody OrderDto order, @PathVariable Long userId) {
        order.setUserId(userId);

        return orderService.createOrder(order);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN') or authentication.principal.id == #userId")
    public List<OrderDto> getAllUserOrders(@PathVariable("userId") Long userId) {

        return orderService.getOrdersByUserId(userId);
    }
}
