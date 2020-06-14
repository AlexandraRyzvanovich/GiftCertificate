package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.service.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/certificate")
public class CertificateController {

    CertificateService service = new CertificateService();

    @GetMapping("/certificate/{id}")
    public ResponseEntity json(@PathVariable("id") Long id) {
        Optional<Certificate> certificate = service.getCertificateById(id);

        if (certificate == null) {
            return new ResponseEntity("No Customer found for ID " + 1, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(certificate.get(), HttpStatus.OK);
    }

}