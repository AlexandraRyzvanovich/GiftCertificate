package com.epam.mjc.service;


import com.epam.mjc.dao.entity.GiftCertificateDto;
import com.epam.mjc.dao.entity.SearchParams;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificateDto getCertificateById(Long id);
    List<GiftCertificateDto> getCertificates(SearchParams searchParams);
    @Transactional
    GiftCertificateDto createCertificate(GiftCertificateDto certificate);
    GiftCertificateDto updateCertificate(Long id, GiftCertificateDto updatedCertificate);
}
