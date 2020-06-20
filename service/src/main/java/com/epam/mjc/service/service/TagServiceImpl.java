package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoIncorrectParamsException;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import com.epam.mjc.service.exception.ServiceIncorrectParamsException;
import com.epam.mjc.service.exception.ServiceNotFoundException;
import com.epam.mjc.service.exception.ValidationException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagDao tagDao;
    private Validator validator;

    public TagServiceImpl(TagDao tagDao, Validator validator) {
        this.tagDao = tagDao;
        this.validator = validator;
    }

    public Tag getTagById(long id) {
        Tag tag;
        try {
            tag = tagDao.getById(id);
        } catch (DaoNotFoundException e) {
            throw new ServiceNotFoundException(e.getMessage());
        }
        return tag;
    }

    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    public Tag createTag(Tag tag ) {
        try {
            validator.validate(tag);
            Long tagId = tagDao.create(tag);
            return tagDao.getById(tagId);
        } catch (DaoIncorrectParamsException e) {
            throw new ServiceIncorrectParamsException(e.getMessage());
        } catch (DaoNotFoundException e) {
            throw new ServiceNotFoundException(e.getMessage());
        }
    }

    public boolean deleteTagById(long id) throws ServiceNotFoundException {
        try {
            return tagDao.deleteById(id);
        } catch (DaoNotFoundException e) {
            throw new ServiceNotFoundException(e.getMessage());
        }
    }
}
