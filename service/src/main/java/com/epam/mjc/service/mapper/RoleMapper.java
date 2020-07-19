package com.epam.mjc.service.mapper;

import com.epam.mjc.dao.dto.RoleDto;
import com.epam.mjc.dao.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoleMapper {
    @Autowired
    private ModelMapper mapper;

    public RoleEntity toEntity(RoleDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, RoleEntity.class);
    }

    public RoleDto toDto(RoleEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, RoleDto.class);
    }
}
