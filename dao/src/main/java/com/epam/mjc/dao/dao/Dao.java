package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.entity.Identifiable;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(long id);
    List<T> getAll();
    T update(T item) throws DaoException;
    T create(T item) throws DaoException;
    boolean delete(T item) throws DaoException;
}
