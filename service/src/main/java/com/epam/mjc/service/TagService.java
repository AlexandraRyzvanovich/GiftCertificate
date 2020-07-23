package com.epam.mjc.service;

import com.epam.mjc.dao.dto.TagDto;

import java.math.BigInteger;
import java.util.List;

public interface TagService {
    TagDto getTagById(Long id);
    List<TagDto> getAllTags(Integer size, Integer pageNumber);
    TagDto createTag(TagDto tagDto);
    String deleteTagById(Long id);
    TagDto getMostPopularAndExpensiveTag();
    BigInteger countTags();
}
