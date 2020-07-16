package com.epam.mjc.service.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.dao.entity.UserEntity;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, OrderDao orderDao, UserMapper mapper) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.mapper = mapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Long id = userDao.createUser(mapper.toEntity(userDto));
        if(id == null) {
            throw new IncorrectParamsException("Impossible to create user with given params");
        }
        return mapper.toDto(userDao.getUserById(id));
    }
    @Override
    public UserDto getById(Long id) {

        return mapper.toDto(userDao.getUserById(id));
    }


    @Override
    public List<UserDto> getAllUsers() {

        return userDao.getAllUsers().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UserEntity persistedUserEntity = userDao.getUserById(id);
        UserEntity userEntityToUpdate = userConverter(persistedUserEntity, mapper.toEntity(userDto));

        return mapper.toDto(userDao.updateUser(userEntityToUpdate));
    }

    private static UserEntity userConverter(UserEntity persistedUserEntity, UserEntity updatedUserEntity) {
        String name = updatedUserEntity.getName();
        String surname = updatedUserEntity.getSurname();
        if (name != null) {
            persistedUserEntity.setName(name);
        }
        if (surname != null) {
            updatedUserEntity.setSurname(surname);
        }
        return persistedUserEntity;
    }
}
