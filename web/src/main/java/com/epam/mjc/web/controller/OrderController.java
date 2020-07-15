package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.OrderEntity;
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
    public OrderEntity getOrderById(@PathVariable("id") long id) {

        return orderService.getOrderById(id);
    }

    @GetMapping()
    public List<OrderEntity> getAllOrders() {

        return orderService.getOrders();
    }

    @PostMapping()
    public OrderEntity createOrder(@RequestBody OrderEntity orderEntity) {

        return orderService.createOrder(orderEntity);
    }
}
