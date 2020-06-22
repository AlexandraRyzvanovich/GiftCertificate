package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.GiftCertificateDao;
import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoIncorrectParamsException;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import com.epam.mjc.service.exception.ServiceIncorrectParamsException;
import com.epam.mjc.service.exception.ServiceNotFoundException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
@EnableTransactionManagement
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private GiftCertificateDao certificateDao;
    private TagDao tagDao;
    private Validator validator;

    public GiftCertificateServiceImpl( GiftCertificateDao certificateDao, TagDao tagDao, Validator validator) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.validator = validator;
    }

    @Override
    public GiftCertificate getCertificateById(long id) {
        GiftCertificate certificate;
        try {
            certificate = certificateDao.getById(id);
        } catch (DaoNotFoundException e) {
            throw new ServiceNotFoundException(e.getMessage());
        }
        List<Tag> tags = tagDao.getAllTagsByCertificateId(certificate.getId());
        if(!CollectionUtils.isEmpty(tags)) {
            certificate.setTags(tags);
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
    public GiftCertificate createCertificate(GiftCertificate certificate)  {
        try {
            validator.validate(certificate);
            Long createdId = certificateDao.create(certificate);
            List<Tag> tags = certificate.getTags();
            if(!CollectionUtils.isEmpty(tags)) {
                for (Tag tag : tags) {
                    Tag foundTag = (tagDao.getByName(tag.getName()));
                    if ( foundTag == null) {
                        Long createdTagId = tagDao.create(tag);
                        certificateDao.createCertificateTag(createdId, createdTagId);
                    } else {
                        certificateDao.createCertificateTag(createdId, tag.getId());
                    }
                }
            }
            return getCertificateById(createdId);
        } catch (DaoIncorrectParamsException e ) {
            throw new ServiceIncorrectParamsException(e.getMessage());
        } catch ( DaoNotFoundException e) {
            throw new ServiceNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean deleteCertificateById(long id)  {
        try {
            boolean result =  certificateDao.deleteById(id);
        } catch ( DaoNotFoundException e) {
            throw new ServiceNotFoundException(e.getMessage());
        }
        return true;
    }

    @Override
    public GiftCertificate updateCertificate(Long id, GiftCertificate updatedCertificate) {
        try {
            GiftCertificate persistedCertificate = certificateDao.getById(id);
            if( persistedCertificate.equals(updatedCertificate)) {
                return persistedCertificate;
            } else {
                GiftCertificate certificateToUpdate = converter(persistedCertificate, updatedCertificate);
                return certificateDao.update(certificateToUpdate);
            }
        } catch (DaoIncorrectParamsException e) {
            throw new ServiceNotFoundException( e.getMessage());
        } catch (DaoNotFoundException e) {
            throw new ServiceNotFoundException(e.getMessage());
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

