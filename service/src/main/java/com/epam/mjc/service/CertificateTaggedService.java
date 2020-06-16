package com.epam.mjc.service;

import com.epam.mjc.dao.dao.impl.CertificateTaggedDtoDaoImpl;
import com.epam.mjc.dao.dto.CertificatedTaggedDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateTaggedService {
    private CertificateTaggedDtoDaoImpl certificateTaggedDtoDao;

    public CertificateTaggedService(CertificateTaggedDtoDaoImpl certificateTaggedDtoDao) {
        this.certificateTaggedDtoDao = certificateTaggedDtoDao;
    }
    public Optional<CertificatedTaggedDto> getCertificateTaggedById(long id) {
        return certificateTaggedDtoDao.getById(id);
    }
    public List<CertificatedTaggedDto> getAllCertificateTagged() {
        return certificateTaggedDtoDao.getAll();
    }
}
