package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {

        return userService.getById(id);
    }

    @GetMapping()
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @PostMapping()
    public UserDto createUser(@RequestBody UserDto userDto) {

        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {

        return userService.updateUser(id, userDto);
    }

    @GetMapping("/orders/{userId}")
    public List<OrderDto> getAllUserOrders(@PathVariable("userId") Long userId) {

        return orderService.getOrdersByUserId(userId);
    }
}

