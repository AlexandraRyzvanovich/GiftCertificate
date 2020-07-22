package com.epam.mjc.service;

import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.dao.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userEntity);
    UserDto getById(Long id);
    PageDto<UserDto> getAllUsers(Integer size, Integer pageNumber);
    UserDto updateUser(Long id, UserDto userDto);

    UserDto findUserByEmail(String email);

    UserDto register(UserDto user);
}
