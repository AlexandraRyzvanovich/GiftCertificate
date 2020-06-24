package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortParams;
import com.epam.mjc.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private GiftCertificateService service;

    @GetMapping("/{id}")
    public GiftCertificate getCertificateById(@PathVariable("id") long id) {

        return service.getCertificateById(id);
    }

    @GetMapping()
    public List<GiftCertificate> getAllCertificates(@RequestParam(required = false, name = "tags") List<String> tags,
                                                    @RequestParam(required = false, name = "text") String text,
                                                    @Sort SortParams params) {
        SearchParams searchParams = new SearchParams(tags, text, params);

        return service.getCertificates(searchParams);
    }

    @PostMapping()
    public GiftCertificate createCertificate(@RequestBody GiftCertificate certificate) {

        return service.createCertificate(certificate);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCertificateById(@PathVariable("id") long id) {

        return service.deleteCertificateById(id);
    }

    @PutMapping("/{id}")
    public GiftCertificate updateCertificate(@PathVariable("id") Long id, @RequestBody GiftCertificate certificate)  {

        return  service.updateCertificate(id, certificate);
    }
}