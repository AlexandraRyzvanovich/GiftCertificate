package com.epam.mjc.service;

import com.epam.mjc.dao.dao.impl.CertificateDaoImpl;
import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.dao.exception.DaoException;
import com.epam.mjc.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {
    private CertificateDaoImpl certificateDao;

    public CertificateService(CertificateDaoImpl certificateDao) {
        this.certificateDao = certificateDao;
    }

    public Optional<Certificate> getCertificateById(long id) {
        return certificateDao.getById(id);
    }

    public List<Certificate> getAllCertificates() {
        return certificateDao.getAll();
    }

    public Certificate createCertificate(Certificate certificate) throws ServiceException {
        try {
            return certificateDao.create(certificate);
        } catch (DaoException e) {
            throw new ServiceException("Exception occurred while creating certificate", e.getCause());
        }
    }

    public boolean deleteCertificateById(long id) throws ServiceException {
        try {
            return certificateDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception occurred while creating certificate", e.getCause());
        }
    }
    public boolean deleteCertificate(Certificate certificate) throws ServiceException {
        try {
            return certificateDao.delete(certificate);
        } catch (DaoException e) {
            throw new ServiceException("Exception occurred while creating certificate", e.getCause());
        }
    }
}

