package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SorterParams;
import com.epam.mjc.service.service.GiftCertificateService;
import com.epam.mjc.service.exception.ServiceException;
import com.epam.mjc.web.exception.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private GiftCertificateService service;

    @GetMapping("/{id}")
    public GiftCertificate getCertificateById(@PathVariable("id") long id) {
        Optional<GiftCertificate> certificate = service.getCertificateById(id);

        return certificate.get();
    }

    @GetMapping()
    public List<GiftCertificate> getAllCertificates(@RequestParam(required = false, name = "tags") List<String> tags,
                                                    @RequestParam(required = false, name = "text") String text,
                                                    @Sort SorterParams params) {
        SearchParams searchParams = new SearchParams(tags, text, params);

        return service.getCertificates(searchParams);
    }

    @PostMapping()
    public GiftCertificate createCertificate(@RequestBody GiftCertificate certificate) {
        Optional<GiftCertificate> createdCertificate = Optional.empty();
        try {
            createdCertificate = service.createCertificate(certificate);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return createdCertificate.get();
    }

    @DeleteMapping("/{id}")
    public boolean deleteCertificateById(@PathVariable("id") long id) {
        boolean result = false;
        try {
            result = service.deleteCertificateById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PutMapping("/{id}")
    public GiftCertificate updateCertificate(@PathVariable("id") Long id, @RequestBody GiftCertificate certificate) throws ControllerException {
        try {
            service.updateCertificate(id, certificate);
        } catch (ServiceException e) {
            throw new ControllerException("Failed updating certificate");
        }
        return certificate;
    }

  /*  @GetMapping("/filter")
    public List<GiftCertificate> search(@RequestParam(required = false, value = "sortParameters") SortParameter sortParameter) {

        service.sortCertificates(sortParameter);

    }*/
}