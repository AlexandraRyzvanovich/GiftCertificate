package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.service.CertificateService;
import com.epam.mjc.service.exception.ServiceException;
import com.epam.mjc.web.exception.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService service;

    @GetMapping("/{id}")
    public ResponseEntity getCertificateById(@PathVariable("id") long id) {
        Optional<Certificate> certificate = service.getCertificateById(id);

        if (!certificate.isPresent()) {
            return new ResponseEntity("No Certificate found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(certificate.get(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAllCertificates() {
        List<Certificate> certificate = service.getAllCertificates();

        if (certificate == null) {
            return new ResponseEntity("Not found any certificates " + 1, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(certificate, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createCertificate(@RequestBody Certificate certificate) {
        Certificate createdCertificate = null;
        try {
            createdCertificate = service.createCertificate(certificate);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return createdCertificate.getId() != null ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCertificateById(@PathVariable("id") long id) {
        try {
            boolean result = service.deleteCertificateById(id);
            if (result != true) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteCertificate(@RequestBody Certificate certificate) throws ControllerException {
        try {
            boolean result = service.deleteCertificate(certificate);
            if (result != true) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new ControllerException("Failed deleting certificate");
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity updateCertificate(@RequestBody Certificate certificate) throws ControllerException {
        try {
            service.updateCertificate(certificate);
        } catch (ServiceException e) {
            throw new ControllerException("Failed updating certificate");
        }
        return new ResponseEntity(certificate, HttpStatus.OK);
    }
}