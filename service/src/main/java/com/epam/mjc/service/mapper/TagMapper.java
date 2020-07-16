package com.epam.mjc.service.mapper;

import com.epam.mjc.dao.dto.TagDto;
import com.epam.mjc.dao.entity.TagEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagMapper {
    @Autowired
    private ModelMapper mapper;

    public TagEntity toEntity(TagDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, TagEntity.class);
    }

    public TagDto toDto(TagEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, TagDto.class);
    }
}
