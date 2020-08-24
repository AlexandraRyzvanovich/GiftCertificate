package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortParams;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.web.linkbuilder.GiftCertificateLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private GiftCertificateService service;
    @Autowired
    private GiftCertificateLinkBuilder certificateLinkBuilder;

    @GetMapping("/{id}")
    public GiftCertificateDto getCertificateById(@PathVariable("id") long id) {

        return certificateLinkBuilder.addLinksToDto(service.getCertificateById(id));
    }

    @GetMapping()
    public PageDto<GiftCertificateDto> getAllCertificates(@RequestParam(required = false, name = "tags") List<String> tags,
                                      @RequestParam(required = false, name = "text") String text,
                                      @Sort SortParams params,
                                      @RequestParam(name = "size", defaultValue = "5") Integer size,
                                      @RequestParam(name = "page", defaultValue = "1") Integer pageNumber) {
        PageDto<GiftCertificateDto> pageDto = new PageDto<>();
        SearchParams searchParams = new SearchParams(tags, text, params);
        List<GiftCertificateDto> certificates = service.getCertificates(searchParams, size, pageNumber);
        pageDto.setItems(certificateLinkBuilder.addLinksToList(certificates));
        int certificatesCount = service.countCertificates(searchParams);
        pageDto.setSize(certificatesCount);
        int pageCount = pageDto.getPageCount(certificatesCount, size);
        pageDto.setCurrentPage(pageNumber);
        pageDto.setPageCount(pageCount);

        return pageDto;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public GiftCertificateDto createCertificate(@RequestBody @Valid GiftCertificateDto certificate) {
        return certificateLinkBuilder.addLinksToDto(service.createCertificate(certificate));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public GiftCertificateDto updateCertificate(@PathVariable("id") Long id, @RequestBody @Valid GiftCertificateDto certificate) {

        return certificateLinkBuilder.addLinksToDto(service.updateCertificate(id, certificate));
    }
}