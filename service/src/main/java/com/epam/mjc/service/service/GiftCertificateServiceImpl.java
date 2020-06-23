package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.GiftCertificateDao;
import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoIncorrectParamsException;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
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
        GiftCertificate certificate = certificateDao.getById(id);
        if(certificate == null) {
            throw new NotFoundException("Certificate with id" + id + "not found");
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
            Validator.validateCertificate(certificate);
            Long createdId = certificateDao.create(certificate);
            if( createdId == null) {
                throw new IncorrectParamsException("Exception failed while executing create certificate query");
            }
            List<Tag> tags = certificate.getTags();
            if(!CollectionUtils.isEmpty(tags)) {
                for (Tag tag : tags) {
                    Tag foundTag = (tagDao.getByName(tag.getName()));
                    if ( foundTag == null) {
                        Long createdTagId = tagDao.create(tag);
                        if(createdTagId == null) {
                            throw new IncorrectParamsException("Exception failed while executing create tag query");
                        }
                        certificateDao.createCertificateTag(createdId, createdTagId);
                    } else {
                        certificateDao.createCertificateTag(createdId, foundTag.getId());
                    }
                }
            }
            return getCertificateById(createdId);
    }

    @Override
    public boolean deleteCertificateById(long id)  {
        boolean result =  certificateDao.deleteById(id);
        if(!result) {
            throw new NotFoundException("Impossible to delete certificate with id = " + id);
        }

        return true;
    }

    @Override
    public GiftCertificate updateCertificate(Long id, GiftCertificate updatedCertificate) {
            GiftCertificate persistedCertificate = certificateDao.getById(id);
            if(persistedCertificate == null) {
                throw new NotFoundException("There are no such certificate with id" + id);
            }
            if(persistedCertificate.equals(updatedCertificate)) {
                return persistedCertificate;
            } else {
                GiftCertificate certificateToUpdate = converter(persistedCertificate, updatedCertificate);
                Validator.validateCertificate(certificateToUpdate);
                boolean result = certificateDao.update(certificateToUpdate);
                List<Tag> tags = updatedCertificate.getTags();
                if(!CollectionUtils.isEmpty(tags)) {
                    for (Tag tag : tags) {
                        Tag foundTag = (tagDao.getByName(tag.getName()));
                        if ( foundTag == null) {
                            Long createdTagId = tagDao.create(tag);
                            if(createdTagId == null) {
                                throw new IncorrectParamsException("Exception failed while executing create tag query");
                            }
                            if(!certificateDao.createCertificateTag(id, createdTagId));
                        } else {
                            if(certificateDao.isCertificateHasTag(id, foundTag.getId())) {
                                throw new IncorrectParamsException("Key value pair of certificate and tag has already been added");
                            }
                            certificateDao.createCertificateTag(id, foundTag.getId());
                        }
                    }
                }
                if(!result) {
                    throw new IncorrectParamsException("Impossible to update certificate. Exception occurred.");
                }
                return getCertificateById(id);
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
        return persistedCertificate;
    }
}

