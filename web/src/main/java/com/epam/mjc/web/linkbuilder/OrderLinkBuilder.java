package com.epam.mjc.web.linkbuilder;

import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.web.controller.OrderController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderLinkBuilder implements LinkBuilder<OrderDto> {
    @Override
    public CollectionModel<OrderDto> addLinksToList(List<OrderDto> orderDtoList) {
        for (OrderDto order: orderDtoList) {
            addLinksToDto(order);
        }
        return CollectionModel.of(
                orderDtoList);
    }

    @Override
    public OrderDto addLinksToDto(OrderDto orderDto) {
        orderDto.add(linkTo(methodOn(OrderController.class).getOrderById(orderDto.getId())).withSelfRel());
        orderDto.add(linkTo(methodOn(OrderController.class).createOrder(orderDto, orderDto.getUserId())).withRel("createOrder"));
        orderDto.add(linkTo(methodOn(OrderController.class).getAllUserOrders(orderDto.getUserId(), null, null)).withRel("getAllUsersOrders"));

        return orderDto;
    }
}
