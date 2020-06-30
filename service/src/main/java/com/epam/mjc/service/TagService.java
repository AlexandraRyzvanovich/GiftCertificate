package com.epam.mjc.service;

import com.epam.mjc.dao.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(long id);
    List<Tag> getAllTags();
    Tag createTag(Tag tag );
    String deleteTagById(String id);
}
