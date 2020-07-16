package com.epam.mjc.service.impl;

import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.dto.TagDto;
import com.epam.mjc.dao.entity.TagEntity;
import com.epam.mjc.service.TagService;
import com.epam.mjc.service.exception.EntityAlreadyExistsException;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    private final TagMapper mapper;

    public TagServiceImpl(TagDao tagDao, TagMapper mapper) {
        this.tagDao = tagDao;
        this.mapper = mapper;
    }

    @Override
    public TagDto getTagById(Long id) {
        TagEntity tagEntity = tagDao.getById(id);
        if(tagEntity == null) {
            throw new NotFoundException("No tag found with id " + id);
        }

        return mapper.toDto(tagEntity);
    }

    @Override
    public List<TagDto> getAllTags() {
        return tagDao.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TagDto createTag(TagDto tagDto) {
        TagEntity foundTagEntity = tagDao.getByName(tagDto.getName());
        if(foundTagEntity != null) {
            throw  new EntityAlreadyExistsException("Tag with name " + tagDto.getName() + " already exists");
        }
            Long tagId = tagDao.create(mapper.toEntity(tagDto));
            if(tagId == null) {
                throw new IncorrectParamsException("Impossible to create tag with given params");
            }
            return mapper.toDto(tagDao.getById(tagId));

    }
    @Override
    public String deleteTagById(Long id) {
        return "Tag has been successfully deleted";
    }
}
