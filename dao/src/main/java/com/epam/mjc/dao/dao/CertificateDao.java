package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CertificateDao {
    Optional<Certificate> getById(long id);

    List<Certificate> getAll();

    Certificate update(Certificate item) throws DaoException;

    Certificate create(Certificate item) throws DaoException;

    boolean delete(Certificate item) throws DaoException;

    boolean deleteById(long id) throws DaoException;
}
