package com.epam.mjc.service.service;

import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.ServiceNotFoundException;

import java.util.List;

public interface TagService {
    Tag getTagById(long id);
    List<Tag> getAllTags();
    Tag createTag(Tag tag ) throws ServiceNotFoundException;
    boolean deleteTagById(long id) throws ServiceNotFoundException;
}
