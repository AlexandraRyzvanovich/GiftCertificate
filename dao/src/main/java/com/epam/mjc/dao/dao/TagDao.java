package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface TagDao {
    Optional<Tag> getById(long id);
    List<Tag> getAllTagsByCertificateId(long id);
    Optional<Tag> getByName (String name);
    List<Tag> getAll();
    Long create(Tag item) throws DaoException;
    boolean deleteById(long id) throws DaoException;
}
