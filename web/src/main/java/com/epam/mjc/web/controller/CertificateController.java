package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortParams;
import com.epam.mjc.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Qualifier("giftCertificateServiceImpl")
    @Autowired
    private GiftCertificateService service;

    @GetMapping("/{id}")
    public GiftCertificateDto getCertificateById(@PathVariable("id") long id) {

        return service.getCertificateById(id);
    }
    @GetMapping()
    public List<GiftCertificateDto> getAllCertificates(@RequestParam(required = false, name = "tags") List<String> tags,
                                                          @RequestParam(required = false, name = "text") String text,
                                                          @Sort SortParams params) {
        SearchParams searchParams = new SearchParams(tags, text, params);

        return service.getCertificates(searchParams);
    }

    @PostMapping()
    public GiftCertificateDto createCertificate(@RequestBody @Valid GiftCertificateDto certificate) {
        return service.createCertificate(certificate);
    }

    @PutMapping("/{id}")
    public GiftCertificateDto updateCertificate(@PathVariable("id") Long id, @RequestBody @Valid GiftCertificateDto certificate)  {

        return  service.updateCertificate(id, certificate);
    }
}