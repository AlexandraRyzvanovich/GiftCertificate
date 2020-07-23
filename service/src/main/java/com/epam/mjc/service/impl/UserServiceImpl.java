package com.epam.mjc.service.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.RoleDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.dao.entity.RoleEntity;
import com.epam.mjc.dao.entity.UserEntity;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final UserMapper mapper;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDao userDao, OrderDao orderDao, UserMapper mapper, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.mapper = mapper;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
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
    public List<UserDto> getAllUsers(Integer size, Integer pageNumber) {
        List<UserDto> users = userDao.getAllUsers(size, pageNumber).stream().map(mapper::toDto).collect(Collectors.toList());

        return users;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UserEntity persistedUserEntity = userDao.getUserById(id);
        UserEntity userEntityToUpdate = userConverter(persistedUserEntity, mapper.toEntity(userDto));

        return mapper.toDto(userDao.updateUser(userEntityToUpdate));
    }
    @Override
    public UserDto findUserByEmail(String email) {
        UserEntity userEntity = userDao.findByEmail(email);
        return mapper.toDto(userEntity);
    }
    @Override
    public UserDto register(UserDto user) {
        RoleEntity roleUser = roleDao.getRoleByName("USER");
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        UserEntity userEntityToSave = mapper.toEntity(user);
        userEntityToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntityToSave.setRoles(userRoles);
        userEntityToSave.setCreatedDate(LocalDateTime.now());
        Long userId = userDao.createUser(userEntityToSave);

        return getById(userId);
    }
    @Override
    public BigInteger countUsers() {
        return userDao.usersTableSize();
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
