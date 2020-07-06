package com.epam.mjc.service.impl;

import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.User;
import com.epam.mjc.dao.entity.UserFullInfoModel;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.validator.Validator;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        Validator.validateUser(user);
        Long id = userDao.createUser(user);
        if(id == null) {
            throw new IncorrectParamsException("Impossible to create user with given params");
        }
        return userDao.getUserById(id);
    }

    @Override
    public UserFullInfoModel getUserFullInfoModelById(Long id) {
        return userDao.;
    }

    @Override
    public List<UserFullInfoModel> getAllUsers() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
