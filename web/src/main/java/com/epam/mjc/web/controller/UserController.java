package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.User;
import com.epam.mjc.dao.entity.UserFullInfoModel;
import com.epam.mjc.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    @GetMapping("/{id}")
    public UserFullInfoModel getUserById(@PathVariable("id") long id) {

        return service.getUserFullInfoModelById(id);
    }

    @GetMapping()
    public List<User> getAllUsers() {

        return service.getAllUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {

        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {

        return service.updateUser(id, user);
    }
}

