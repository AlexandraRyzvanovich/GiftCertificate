package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.dao.entity.SearchParams;

import java.util.List;

public interface GiftCertificateDao {
    GiftCertificateEntity getById(long id);

    GiftCertificateEntity getByName(String name);

    List<GiftCertificateEntity> getAll(SearchParams searchParams, Integer size, Integer pageNumber);

    GiftCertificateEntity update(GiftCertificateEntity item);

    Long create(GiftCertificateEntity item);

    int countCertificates(SearchParams searchParams);
}
