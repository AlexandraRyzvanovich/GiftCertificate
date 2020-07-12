package com.epam.mjc.service;

import com.epam.mjc.dao.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getById(Long id);

    List<User> getAllUsers();
    User updateUser(Long id, User user);
}
