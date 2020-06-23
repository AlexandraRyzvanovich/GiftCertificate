package com.epam.mjc.service.service;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate getCertificateById(long id);
    List<GiftCertificate> getCertificates(SearchParams searchParams);
    GiftCertificate createCertificate(GiftCertificate certificate);
    GiftCertificate updateCertificate(Long id, GiftCertificate updatedCertificate);
    boolean deleteCertificateById(long id);
}
