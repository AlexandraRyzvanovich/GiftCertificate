package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {
    GiftCertificate getById(long id) throws DaoException;
    Optional<GiftCertificate> getByName(String name);

    List<GiftCertificate> getAll(SearchParams searchParams);

    GiftCertificate update(GiftCertificate item) throws DaoException;

    Long create(GiftCertificate item) throws DaoException;

    boolean deleteById(long id) throws DaoException;

    boolean createCertificateTag(long certificateId, long tagId) throws DaoException;
}
