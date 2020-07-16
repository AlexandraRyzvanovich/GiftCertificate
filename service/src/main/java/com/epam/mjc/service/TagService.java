package com.epam.mjc.service;

import com.epam.mjc.dao.dto.TagDto;

import java.util.List;

public interface TagService {
    TagDto getTagById(Long id);
    List<TagDto> getAllTags();
    TagDto createTag(TagDto tagDto);
    String deleteTagById(Long id);
}
