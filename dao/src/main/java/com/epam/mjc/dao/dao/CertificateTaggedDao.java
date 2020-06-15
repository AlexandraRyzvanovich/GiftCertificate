package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.dto.CertificatedTaggedDto;
import com.epam.mjc.dao.entity.Identifiable;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface CertificateTaggedDao {
    Optional<CertificatedTaggedDto> getById(long id);
    List<CertificatedTaggedDto> getAll();
    CertificatedTaggedDto update(CertificatedTaggedDto item) throws DaoException;
    CertificatedTaggedDto create(CertificatedTaggedDto item) throws DaoException;
    boolean delete(CertificatedTaggedDto item) throws DaoException;

}
