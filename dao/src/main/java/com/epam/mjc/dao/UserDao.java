package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(Long id);
    Long createUser(User user);
    User updateUser(User user);
}
