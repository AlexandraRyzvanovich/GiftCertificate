package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.dao.entity.Identifiable;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(long id);
    List<T> getAll();
    boolean update(T item) throws DaoException;
    boolean create(T item) throws DaoException;
    boolean delete(T item) throws DaoException;
}
