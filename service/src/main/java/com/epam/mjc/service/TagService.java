package com.epam.mjc.service;

import com.epam.mjc.dao.dao.impl.CertificateDaoImpl;
import com.epam.mjc.dao.dao.impl.TagDaoImpl;
import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;
import com.epam.mjc.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private TagDaoImpl tagDao;

    public TagService(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
    }
    public Optional<Tag> getTagById(long id) {
        return tagDao.getById(id);
    }

    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    public Tag createTag(Tag tag ) throws ServiceException {
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

    public boolean deleteTag(Tag tag) throws ServiceException {
        try {
            return tagDao.delete(tag);
        } catch (DaoException e) {
            throw new ServiceException("Exception occurred while creating tag", e.getCause());
        }
    }
}
