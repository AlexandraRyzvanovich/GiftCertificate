package com.epam.mjc.service;

import com.epam.mjc.dao.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(Long id);
    List<Tag> getAllTags();
    Tag createTag(Tag tag );
    String deleteTagById(Long id);
}
