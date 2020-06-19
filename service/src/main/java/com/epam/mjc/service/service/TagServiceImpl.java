package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.TagDaoImpl;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;
import com.epam.mjc.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagDaoImpl tagDao;

    public TagServiceImpl(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
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
            Long tagId = tagDao.create(tag);
            return tagDao.getById(tagId);
        } catch (DaoException e) {
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
