package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.UserEntity;

import java.util.List;

public interface UserDao {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    Long createUser(UserEntity userEntity);
    UserEntity updateUser(UserEntity userEntity);

    UserEntity findByEmail(String email);
}
