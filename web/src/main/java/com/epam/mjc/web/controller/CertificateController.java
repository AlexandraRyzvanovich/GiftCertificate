package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.service.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/certificate")
public class CertificateController {
    private CertificateService service = new CertificateService();

    @GetMapping("/{id}")
    public ResponseEntity getCertificateById(@PathVariable("id") Long id) {
        Optional<Certificate> certificate = service.getCertificateById(id);

        if (!certificate.isPresent()) {
            return new ResponseEntity("No Customer found for ID " + 1, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(certificate.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllCertificates() {
        List<Certificate> certificate = service.getAllCertificates();

        if (certificate == null) {
            return new ResponseEntity("Not found any certificates " + 1, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(certificate, HttpStatus.OK);
    }

    @PostMapping("/addCertificate")
    public ResponseEntity createCertificate(@RequestBody Certificate certificate) {
        boolean result = service.createCertificate(certificate);

        return result == true ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}