package com.epam.mjc.service.service;

import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<Tag> getTagById(long id);
    List<Tag> getAllTags();
    Long createTag(Tag tag ) throws ServiceException;
    boolean deleteTagById(long id) throws ServiceException;
}