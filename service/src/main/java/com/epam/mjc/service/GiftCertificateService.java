package com.epam.mjc.service;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate getCertificateById(long id);
    List<GiftCertificate> getCertificates(SearchParams searchParams);
    GiftCertificate createCertificate(GiftCertificate certificate);
    GiftCertificate updateCertificate(String id, GiftCertificate updatedCertificate);
    String deleteCertificateById(String id);
}
