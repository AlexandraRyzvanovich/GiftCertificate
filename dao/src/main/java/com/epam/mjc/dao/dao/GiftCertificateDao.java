package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.exception.DaoIncorrectParamsException;
import com.epam.mjc.dao.exception.DaoNotFoundException;

import java.util.List;

public interface GiftCertificateDao {
    GiftCertificate getById(long id) throws DaoNotFoundException;

    List<GiftCertificate> getAll(SearchParams searchParams);

    GiftCertificate update(GiftCertificate item) throws DaoIncorrectParamsException;

    Long create(GiftCertificate item) throws DaoIncorrectParamsException;

    boolean deleteById(long id) throws DaoNotFoundException;

    boolean createCertificateTag(long certificateId, long tagId) throws DaoIncorrectParamsException;
}
