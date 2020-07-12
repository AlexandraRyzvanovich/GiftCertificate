package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;

import java.util.List;

public interface GiftCertificateDao {
    GiftCertificate getById(long id);

    GiftCertificate getByName(String name);

    List<GiftCertificate> getAll(SearchParams searchParams);

    GiftCertificate update(GiftCertificate item);

    Long create(GiftCertificate item);

    void createCertificateTag(Long certificateId, Long tagId);

//    boolean createCertificateTag(long certificateId, long tagId);
}
