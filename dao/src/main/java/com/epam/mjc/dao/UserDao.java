package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<UserEntity> getAllUsers(Integer size, Integer pageNumber);
    int usersTableSize();
    Optional<UserEntity> getUserById(Long id);
    Long createUser(UserEntity userEntity);
    UserEntity updateUser(UserEntity userEntity);
    Optional<UserEntity> findByEmail(String email);
}
