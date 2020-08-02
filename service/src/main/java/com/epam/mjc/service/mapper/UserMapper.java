package com.epam.mjc.service.mapper;

import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.dao.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    public UserEntity toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserEntity.class);
    }

    public UserDto toDto(UserEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }
}
