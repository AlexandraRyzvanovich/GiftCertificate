package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.GiftCertificateDao;
import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;
import com.epam.mjc.service.exception.ServiceException;
import com.epam.mjc.service.exception.ValidatorException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@EnableTransactionManagement
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private GiftCertificateDao certificateDao;
    private TagDao tagDao;

    public GiftCertificateServiceImpl(@Qualifier("giftCertificateDaoImpl") GiftCertificateDao certificateDao, @Qualifier("tagDaoImpl") TagDao tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }
    @Override
    public Optional<GiftCertificate> getCertificateById(long id) {
        Optional<GiftCertificate> certificate = certificateDao.getById(id);
        if(certificate.isPresent()) {
            List<Tag> tags = tagDao.getAllTagsByCertificateId(certificate.get().getId());
            certificate.get().setTags(tags);
        }
        return certificate;
    }
    @Override
    public List<GiftCertificate> getCertificates(SearchParams searchParams) {
        List<GiftCertificate> certificates = certificateDao.getAll(searchParams);
        for (GiftCertificate certificate: certificates) {
            certificate.setTags(tagDao.getAllTagsByCertificateId(certificate.getId()));
        }
        return certificates;
    }

    @Override
    @Transactional
    public Optional<GiftCertificate> createCertificate(GiftCertificate certificate)  {
        try {
            Validator.validateCertificateProperties(certificate);
            Long createdId = certificateDao.create(certificate);
            List<Tag> tags = certificate.getTags();
            if(!CollectionUtils.isEmpty(tags)) {
                for (Tag tag : tags) {
                    Tag foundTag = (tagDao.getByName(tag.getTagName()));
                    if ( foundTag == null) {
                        Long createdTagId = tagDao.create(tag);
                        certificateDao.createCertificateTag(createdId, createdTagId);
                    } else {
                        certificateDao.createCertificateTag(createdId, tag.getId());
                    }
                }
            }
            return getCertificateById(createdId);
        } catch (DaoException | ValidatorException e) {
           throw new ServiceException("Certificate creation failed");
        }
    }

    @Override
    public boolean deleteCertificateById(long id) throws ServiceException {
        try {
            return certificateDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception occurred while creating certificate", e.getCause());
        }
    }

    @Override
    public GiftCertificate updateCertificate(Long id, GiftCertificate updatedCertificate) throws ServiceException {
        try {
            Optional<GiftCertificate> persistedCertificate = certificateDao.getById(id);
            if(! persistedCertificate.isPresent() || persistedCertificate.get().equals(updatedCertificate)) {
                return persistedCertificate.get();
            } else {
                GiftCertificate certificateToUpdate = converter(persistedCertificate.get(), updatedCertificate);
                return certificateDao.update(certificateToUpdate);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception occurred while updating certificate", e.getCause());
        }
    }

    private static GiftCertificate converter (GiftCertificate persistedCertificate, GiftCertificate updatedCertificate) {
        String name = updatedCertificate.getName();
        String description = updatedCertificate.getDescription();
        BigDecimal price = updatedCertificate.getPrice();
        Integer validDays = updatedCertificate.getValidDays();
        if(name != null) {
            persistedCertificate.setName(name);
        }
        if(description != null) {
            persistedCertificate.setDescription(description);
        }
        if(price != null) {
            persistedCertificate.setPrice(price);
        }
        if(validDays != null) {
            persistedCertificate.setValidDays(validDays);
        }

        return updatedCertificate;
    }
}

