package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.TagDaoImpl;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;
import com.epam.mjc.service.exception.ServiceException;
import com.epam.mjc.service.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private TagDaoImpl tagDao;

    public TagServiceImpl(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
    }

    public Optional<Tag> getTagById(long id) {
        return tagDao.getById(id);
    }

    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    public Long createTag(Tag tag ) throws ServiceException {
        try {
            return tagDao.create(tag);
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
