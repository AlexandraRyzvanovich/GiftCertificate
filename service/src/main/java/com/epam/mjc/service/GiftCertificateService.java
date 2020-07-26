package com.epam.mjc.service;


import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.dao.entity.SearchParams;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificateDto getCertificateById(Long id);
    List<GiftCertificateDto> getCertificates(SearchParams searchParams, Integer size, Integer pageNumber);
    @Transactional
    GiftCertificateDto createCertificate(GiftCertificateDto certificate);
    GiftCertificateDto updateCertificate(Long id, GiftCertificateDto updatedCertificate);
    int countOrders(SearchParams params);
}
