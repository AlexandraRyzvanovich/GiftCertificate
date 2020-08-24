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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    private final TagMapper mapper;

    public TagServiceImpl(TagDao tagDao, TagMapper mapper) {
        this.tagDao = tagDao;
        this.mapper = mapper;
    }

    @Override
    public TagDto getTagById(Long id) {

        return tagDao.getById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("No tag found with id " + id));
    }

    @Override
    public List<TagDto> getAllTags(Integer size, Integer pageNumber) {
        return tagDao.getAll(size, pageNumber).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TagDto createTag(TagDto tagDto) {
        Optional<TagEntity> tagEntity = tagDao.getByName(tagDto.getName());
        if (tagEntity.isPresent()) {
            throw new EntityAlreadyExistsException("Tag with name " + tagDto.getName() + " already exists");
        }
        Long tagId = tagDao.create(mapper.toEntity(tagDto));
        if (tagId == null) {
            throw new IncorrectParamsException("Impossible to create tag with given params");
        }
        return tagDao.getById(tagId).map(mapper::toDto).orElse(null);

    }

    @Override
    public String deleteTagById(Long id) {
        tagDao.deleteById(id);
        return "Tag has been successfully deleted";
    }

    @Override
    public TagDto getMostPopularAndExpensiveTag() {
        List<TagEntity> tag = tagDao.getMostPopularAndExpensiveTag();
        if (tag != null) {
            return mapper.toDto(tag.get(0));
        }
        return null;
    }

    @Override
    public TagDto updateTag(Long id, TagDto updatedTagDto) {
        TagDto persistedTag = tagDao.getById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("Tag with id " + id + " not found"));
        updatedTagDto.setId(id);
        if(persistedTag.equals(updatedTagDto)) {
            return persistedTag;
        }
        TagEntity tagEntity = mapper.toEntity(updatedTagDto);
        return mapper.toDto(tagDao.updateTag(tagEntity));
    }

    @Override
    public int countTags() {
        return tagDao.countTags();
    }
}
