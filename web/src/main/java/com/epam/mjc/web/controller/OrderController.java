package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.web.linkbuilder.OrderLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private OrderLinkBuilder orderLinkBuilder;


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #userId")
    public OrderDto getOrderById(@PathVariable("id") Long id) {

        return orderLinkBuilder.addLinksToDto(orderService.getOrderById(id));
    }

    @PostMapping()
    @PreAuthorize("authentication.principal.id == #userId")
    public OrderDto createOrder(@RequestBody OrderDto order, @PathVariable Long userId) {
        order.setUserId(userId);

        return orderLinkBuilder.addLinksToDto(orderService.createOrder(order));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN') or authentication.principal.id == #userId")
    public PageDto<OrderDto> getAllUserOrders(@PathVariable("userId") Long userId,
                                              @RequestParam(name = "size", defaultValue = "5") Integer size,
                                              @RequestParam(name = "number", defaultValue = "1") Integer pageNumber) {
        List<OrderDto> orders = orderService.getOrdersByUserId(userId, size, pageNumber);
        BigInteger allOrdersSize = orderService.countOrders(userId);

        return new PageDto<>(orderLinkBuilder.addLinksToList(orders), allOrdersSize);
    }
}
