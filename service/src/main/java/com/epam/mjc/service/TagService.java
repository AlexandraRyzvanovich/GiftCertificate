package com.epam.mjc.service;

import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.NotFoundException;

import java.util.List;

public interface TagService {
    Tag getTagById(Long id);
    List<Tag> getAllTags();
    Tag createTag(Tag tag );

    String deleteTagById(Long id) throws NotFoundException;
}
