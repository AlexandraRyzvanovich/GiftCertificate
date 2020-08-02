package com.epam.mjc.service.mapper;

import com.epam.mjc.dao.dto.OrderDto;
import com.epam.mjc.dao.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderMapper {
    @Autowired
    private ModelMapper mapper;

    public OrderEntity toEntity(OrderDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, OrderEntity.class);
    }

    public OrderDto toDto(OrderEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderDto.class);
    }
}
