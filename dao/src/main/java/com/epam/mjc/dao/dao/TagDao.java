package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;

public interface TagDao {
    Tag getById(long id);
    List<Tag> getAllTagsByCertificateId(long id);
    Tag getByName (String name);
    List<Tag> getAll();
    Long create(Tag item) throws DaoException;
    boolean deleteById(long id) throws DaoException;
}
