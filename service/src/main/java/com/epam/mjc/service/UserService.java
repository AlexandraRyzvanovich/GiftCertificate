package com.epam.mjc.service;

import com.epam.mjc.dao.entity.User;
import com.epam.mjc.dao.entity.UserFullInfoModel;

import java.util.List;

public interface UserService {
    User createUser(User user);
    UserFullInfoModel getUserFullInfoModelById(Long id);
    List<UserFullInfoModel> getAllUsers();
    User updateUser(User user);
}