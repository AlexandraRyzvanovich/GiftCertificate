package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable("id") long id) {

        return orderService.getOrderById(id);
    }

    @GetMapping()
    public List<OrderDto> getAllOrders() {

        return orderService.getOrders();
    }

    @PostMapping()
    public OrderDto createOrder(@RequestBody OrderDto order) {

        return orderService.createOrder(order);
    }
}
