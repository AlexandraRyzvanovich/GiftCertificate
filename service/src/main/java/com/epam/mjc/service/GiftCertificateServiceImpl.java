package com.epam.mjc.service;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@EnableTransactionManagement
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private GiftCertificateDao certificateDao;
    private TagDao tagDao;
    private Validator validator;

    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, TagDao tagDao, Validator validator) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.validator = validator;
    }

    @Override
    public GiftCertificate getCertificateById(long id) {
        GiftCertificate certificate = certificateDao.getById(id);
        if (certificate == null) {
            throw new NotFoundException("Certificate with id" + id + "not found");
        }
        List<Tag> tags = tagDao.getAllTagsByCertificateId(certificate.getId());
        if (!CollectionUtils.isEmpty(tags)) {
            certificate.setTags(tags);
        }
        return certificate;
    }

    @Override
    public List<GiftCertificate> getCertificates(SearchParams searchParams) {
        List<GiftCertificate> certificates = certificateDao.getAll(searchParams);
        for (GiftCertificate certificate : certificates) {
            certificate.setTags(tagDao.getAllTagsByCertificateId(certificate.getId()));
        }
        return certificates;
    }

    @Override
    @Transactional
    public GiftCertificate createCertificate(GiftCertificate certificate) {
        Validator.validateCertificate(certificate);
        Long createdId = certificateDao.create(certificate);
        if (createdId == null) {
            throw new IncorrectParamsException("Exception failed while executing create certificate query");
        }
        List<Tag> tags = certificate.getTags();
        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                Tag foundTag = (tagDao.getByName(tag.getName()));
                if (foundTag == null) {
                    Long createdTagId = tagDao.create(tag);
                    if (createdTagId == null) {
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
    public boolean deleteCertificateById(long id) {
        boolean result = certificateDao.deleteById(id);
        if (!result) {
            throw new NotFoundException("Impossible to delete certificate with id = " + id);
        }

        return true;
    }

    @Transactional
    @Override
    public GiftCertificate updateCertificate(Long id, GiftCertificate updatedCertificate) {
        GiftCertificate persistedCertificate = certificateDao.getById(id);
        if (persistedCertificate == null) {
            throw new NotFoundException("There are no such certificate with id" + id);
        }
        if (persistedCertificate.equals(updatedCertificate)) {
            return persistedCertificate;
        } else {
            GiftCertificate certificateToUpdate = certificateConverter(persistedCertificate, updatedCertificate);
            Validator.validateCertificate(certificateToUpdate);
            boolean result = certificateDao.update(certificateToUpdate);
            if (!result) {
                throw new IncorrectParamsException("Impossible to update certificate. Exception occurred.");
            }
            List<Tag> updatedTags = updatedCertificate.getTags();
            List<Tag> persistedTags = tagDao.getAllTagsByCertificateId(id);
            List<Tag> finalTagList = tagsConverter(id,persistedTags, updatedTags);
            saveTags(id, finalTagList);
         }
            return getCertificateById(id);
    }

    private static GiftCertificate certificateConverter(GiftCertificate persistedCertificate, GiftCertificate updatedCertificate) {
        String name = updatedCertificate.getName();
        String description = updatedCertificate.getDescription();
        BigDecimal price = updatedCertificate.getPrice();
        Integer validDays = updatedCertificate.getValidDays();
        if (name != null) {
            persistedCertificate.setName(name);
        }
        if (description != null) {
            persistedCertificate.setDescription(description);
        }
        if (price != null) {
            persistedCertificate.setPrice(price);
        }
        if (validDays != null) {
            persistedCertificate.setValidDays(validDays);
        }
        return persistedCertificate;
    }

    private List<Tag> tagsConverter(Long id, List<Tag> persistedTags, List<Tag> updatedTags) {
        List<Tag> tagsToBeSaved = new ArrayList<>();
        for (Tag persisted: persistedTags) {
            for (Tag updated: updatedTags) {
                if(!persisted.getName().equalsIgnoreCase(updated.getName())) {
            tagDao.deleteFromCertificateTag(id, persisted.getId());
                }
            }
        }
        for (Tag updatedTag : updatedTags) {
            if (!persistedTags.contains(updatedTag.getName())) {
                tagsToBeSaved.add(updatedTag);
            }
        }
        return tagsToBeSaved;
    }

    private void saveTags(Long certificateId, List<Tag> tags) {
        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                Tag foundTag = (tagDao.getByName(tag.getName()));
                if (foundTag == null) {
                    Long createdTagId = tagDao.create(tag);
                    if (createdTagId == null) {
                        throw new IncorrectParamsException("Exception failed while executing create tag query");
                    }
                    certificateDao.createCertificateTag(certificateId, createdTagId);
                } else {
                    certificateDao.createCertificateTag(certificateId, foundTag.getId());
                }
            }
        }
    }
}

