package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.dao.entity.SearchParams;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {
    Optional<GiftCertificateEntity> getById(long id);

    Optional<GiftCertificateEntity> getByName(String name);

    List<GiftCertificateEntity> getAll(SearchParams searchParams, Integer size, Integer pageNumber);

    GiftCertificateEntity update(GiftCertificateEntity item);

    Long create(GiftCertificateEntity item);

    int countCertificates(SearchParams searchParams);
}
