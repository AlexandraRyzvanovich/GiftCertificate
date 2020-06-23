package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.exception.DaoIncorrectParamsException;
import com.epam.mjc.dao.exception.DaoNotFoundException;

import java.util.List;

public interface GiftCertificateDao {
    GiftCertificate getById(long id);

    List<GiftCertificate> getAll(SearchParams searchParams);

    boolean update(GiftCertificate item);

    Long create(GiftCertificate item);

    boolean deleteById(long id);

    boolean createCertificateTag(long certificateId, long tagId);

    boolean isCertificateHasTag(long certficateId, long tagId);
}
