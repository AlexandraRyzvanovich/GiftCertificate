package com.epam.mjc.service.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.RoleDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.dao.entity.RoleEntity;
import com.epam.mjc.dao.entity.UserEntity;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
        if (id == null) {
            throw new IncorrectParamsException("Impossible to create user with given params");
        }
        return userDao.getUserById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public UserDto getById(Long id) {

        return userDao.getUserById(id).map(mapper::toDto).orElseThrow(()-> new NotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<UserDto> getAllUsers(Integer size, Integer pageNumber) {

        return userDao.getAllUsers(size, pageNumber).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UserEntity persistedUserEntity = userDao.getUserById(id).orElseThrow(() -> new NotFoundException("User with id " + userDto.getId() + " not found"));
        if (userDto.getPassword() != null) {
            String password = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(password);
        }
        UserEntity userEntityToUpdate = userConverter(persistedUserEntity, mapper.toEntity(userDto));

        return mapper.toDto(userDao.updateUser(userEntityToUpdate));
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userDao.findByEmail(email).map(mapper::toDto).orElseThrow(()-> new NotFoundException("User with email "+ email+" not found"));
    }

    @Override
    public UserDto register(UserDto user) {
        RoleEntity roleUser = roleDao.getRoleByName("ROLE_USER");
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
    public int countUsers() {
        return userDao.usersTableSize();
    }

    private static UserEntity userConverter(UserEntity persistedUserEntity, UserEntity updatedUserEntity) {
        String name = updatedUserEntity.getName();
        String surname = updatedUserEntity.getSurname();
        String email = updatedUserEntity.getEmail();
        String password = updatedUserEntity.getPassword();
        if (name != null) {
            persistedUserEntity.setName(name);
        }
        if (surname != null) {
            updatedUserEntity.setSurname(surname);
        }
        if (email != null) {
            persistedUserEntity.setEmail(email);
        }
        if (password != null) {
            persistedUserEntity.setPassword(password);
        }
        return persistedUserEntity;
    }
}
