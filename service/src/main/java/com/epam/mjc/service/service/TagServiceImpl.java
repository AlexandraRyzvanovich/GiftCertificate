package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;
import com.epam.mjc.service.exception.ServiceException;
import com.epam.mjc.service.exception.ValidatorException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagDao tagDao;
    private Validator validator;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
        validator = new Validator();
    }

    public Tag getTagById(long id) {
        Tag tag = tagDao.getById(id);
        if(tag == null) {
            throw new ServiceException("Tag not found");
        }
        return tag;
    }

    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    public Tag createTag(Tag tag ) throws ServiceException {
        try {
            validator.validate(tag);
            Long tagId = tagDao.create(tag);
            return tagDao.getById(tagId);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException("Exception occurred while creating tag", e.getCause());
        }
    }

    public boolean deleteTagById(long id) throws ServiceException {
        try {
            return tagDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception occurred while creating tag", e.getCause());
        }
    }
}
