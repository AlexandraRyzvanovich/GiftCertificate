package com.epam.mjc.service.service;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.service.exception.ServiceNotFoundException;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate getCertificateById(long id);
    List<GiftCertificate> getCertificates(SearchParams searchParams);
    GiftCertificate createCertificate(GiftCertificate certificate) throws ServiceNotFoundException;
    GiftCertificate updateCertificate(Long id, GiftCertificate updatedCertificate) throws ServiceNotFoundException;
    boolean deleteCertificateById(long id) throws ServiceNotFoundException;
}
