package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.dto.CertificatedTaggedDto;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface TagDao {
    Optional<Tag> getById(long id);
    Optional<Tag> getByName(String name);
    List<Tag> getAll();
    Tag update(Tag item) throws DaoException;
    Tag create(Tag item) throws DaoException;
    boolean delete(Tag item) throws DaoException;
    boolean deleteById(long id) throws DaoException;
}
