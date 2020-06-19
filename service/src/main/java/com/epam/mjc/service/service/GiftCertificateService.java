package com.epam.mjc.service.service;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {
    Optional<GiftCertificate> getCertificateById(long id);
    List<GiftCertificate> getCertificates(SearchParams searchParams);
    Optional<GiftCertificate> createCertificate(GiftCertificate certificate) throws ServiceException;
    GiftCertificate updateCertificate(Long id, GiftCertificate updatedCertificate) throws ServiceException;
    boolean deleteCertificateById(long id) throws ServiceException;
}
