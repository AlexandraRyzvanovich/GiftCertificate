package com.epam.mjc.service.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.User;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final OrderDao orderDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

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
    public User getById(Long id) {

        return userDao.getUserById(id);
    }


    @Override
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    public User updateUser(Long id, User user) {
        User persistedUser = userDao.getUserById(id);
        User userToUpdate = userConverter(persistedUser, user);

        return userDao.updateUser(userToUpdate);
    }

    private static User userConverter(User persistedUser, User updatedUser) {
        String name = updatedUser.getName();
        String surname = updatedUser.getSurname();
        if (name != null) {
            persistedUser.setName(name);
        }
        if (surname != null) {
            updatedUser.setSurname(surname);
        }
        return persistedUser;
    }
}
