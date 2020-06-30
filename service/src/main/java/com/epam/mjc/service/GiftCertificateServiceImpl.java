package com.epam.mjc.service;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.EntityAlreadyExistsException;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private GiftCertificateDao certificateDao;
    private TagDao tagDao;

    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, TagDao tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }

    @Override
    public GiftCertificate getCertificateById(long id) {
        GiftCertificate certificate = certificateDao.getById(id);
        if (certificate == null) {
            throw new NotFoundException("Certificate with id " + id + " not found");
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
        GiftCertificate giftCertificate = certificateDao.getByName(certificate.getName());
        if(giftCertificate != null) {
            throw new EntityAlreadyExistsException("Certificate with such name " + certificate.getName() + " already exists");

        }
        Validator.validateCertificate(certificate);
        Long createdId = certificateDao.create(certificate);
        if (createdId == null) {
            throw new IncorrectParamsException("Exception occurred while executing create certificate query");
        }
        List<Tag> tags = certificate.getTags();
        saveTags(createdId, tags);
        return getCertificateById(createdId);
    }

    @Override
    public String deleteCertificateById(String id) {
        long certificateId;
        try {
            certificateId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NotFoundException("Certificate id " + id + " not found. Incorrect id format was provided.");
        }
        boolean result = certificateDao.deleteById(certificateId);
        if (!result) {
            throw new NotFoundException("Impossible to delete certificate with id = " + id + " Certificate doesn't exists.");
        }
        return "Certificate has been successfully deleted";
    }

    @Override
    @Transactional
    public GiftCertificate updateCertificate(String id, GiftCertificate updatedCertificate) {
        long certificateId;
        try {
            certificateId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NotFoundException("Certificate id " + id + " not found. Incorrect id format was provided.");
        }
        GiftCertificate persistedCertificate = certificateDao.getById(certificateId);
        if (persistedCertificate == null) {
            throw new NotFoundException("Impossible to update certificate with id = " + id + "Certificate doesn't exists.");
        }
        if (persistedCertificate.equals(updatedCertificate)) {
            return persistedCertificate;
        } else {
            GiftCertificate certificateToUpdate = certificateConverter(persistedCertificate, updatedCertificate);
            Validator.validateCertificate(certificateToUpdate);
            boolean result = certificateDao.update(certificateToUpdate);
            if (!result) {
                throw new IncorrectParamsException("Impossible to update certificate.");
            }
            List<Tag> updatedTags = updatedCertificate.getTags();
            List<Tag> persistedTags = tagDao.getAllTagsByCertificateId(certificateId);
            List<Tag> finalTagList = tagsConverter(certificateId,persistedTags, updatedTags);
            saveTags(certificateId, finalTagList);
         }
            return getCertificateById(certificateId);
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

        persistedTags.stream().filter(tag -> updatedTags.stream()
                .noneMatch(tag1 -> tag.getName().equalsIgnoreCase(tag1.getName())))
                .forEach(persisted->tagDao.deleteFromCertificateTag(id, persisted.getId()));

        return updatedTags.stream()
                .filter(tag -> persistedTags.stream()
                .noneMatch(pers -> pers.getName().equalsIgnoreCase(tag.getName())))
                .collect(Collectors.toList());
    }

    private void saveTags(Long certificateId, List<Tag> tags) {
        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                Tag foundTag = (tagDao.getByName(tag.getName()));
                if (foundTag == null) {
                    Validator.validateTag(tag);
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

