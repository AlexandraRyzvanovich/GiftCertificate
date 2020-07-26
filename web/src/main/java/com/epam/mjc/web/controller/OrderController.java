package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.web.linkbuilder.OrderLinkBuilder;
import com.epam.mjc.web.linkbuilder.RoleIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private OrderLinkBuilder orderLinkBuilder;


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and authentication.principal.id == #userId)")
    public OrderDto getOrderById(@PathVariable("id") Long id, @PathVariable Long userId) {
        if(RoleIdentifier.getRoles().contains("ROLE_ADMIN")) {
            return orderLinkBuilder.addLinksToDto(orderService.getOrderById(id, null));
        }
        return orderLinkBuilder.addLinksToDto(orderService.getOrderById(id, userId));
    }

    @PostMapping()
    @PreAuthorize("authentication.principal.id == #userId")
    public OrderDto createOrder(@RequestBody OrderDto order, @PathVariable Long userId) {
        order.setUserId(userId);

        return orderLinkBuilder.addLinksToDto(orderService.createOrder(order));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and authentication.principal.id == #userId)")
    public PageDto<OrderDto> getAllUserOrders(@PathVariable("userId") Long userId,
                                              @RequestParam(name = "size", defaultValue = "5") Integer size,
                                              @RequestParam(name = "number", defaultValue = "1") Integer pageNumber) {
        List<OrderDto> orders = orderService.getOrdersByUserId(userId, size, pageNumber);
        int allOrdersSize = orderService.countOrders(userId);

        return new PageDto<>(orderLinkBuilder.addLinksToList(orders), allOrdersSize);
    }
}
