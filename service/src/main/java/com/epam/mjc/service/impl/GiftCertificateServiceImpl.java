package com.epam.mjc.service.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.entity.GiftCertificateDto;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.service.exception.EntityAlreadyExistsException;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.mapper.GiftCertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao certificateDao;
    private final TagDao tagDao;
    private final GiftCertificateMapper mapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, TagDao tagDao, GiftCertificateMapper mapper) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.mapper = mapper;
    }

    @Override
    public GiftCertificateDto getCertificateById(Long id) {
        GiftCertificateEntity certificate = certificateDao.getById(id);
        if (certificate == null) {
            throw new NotFoundException("Certificate with id " + id + " not found");
        }
        return mapper.toDto(certificate);
    }

    @Override
    public List<GiftCertificateDto> getCertificates(SearchParams searchParams) {
        return (certificateDao.getAll(searchParams)).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GiftCertificateDto createCertificate(GiftCertificateDto certificate) {
        GiftCertificateEntity giftCertificateEntity = certificateDao.getByName(certificate.getName());
        if(giftCertificateEntity != null) {
            throw new EntityAlreadyExistsException("Certificate with such name " + certificate.getName() + " already exists");
        }
        GiftCertificateEntity certificateToBeCreated = new GiftCertificateEntity(certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getValidDays());
        certificateToBeCreated.setCreationDate(LocalDateTime.now());
        if(certificate.getTags()!=null) {
            certificateToBeCreated.setTags(tagsMapper(certificate.getTags()));
        }
        Long createdId = certificateDao.create(certificateToBeCreated);
        if (createdId == null) {
            throw new IncorrectParamsException("Exception occurred while executing create certificate query");
        }

        return getCertificateById(createdId);
    }

    @Override
    @Transactional
    public GiftCertificateEntity updateCertificate(Long id, GiftCertificateEntity updatedCertificate) {
        GiftCertificateEntity persistedCertificate = certificateDao.getById(id);
        if (persistedCertificate == null) {
            throw new NotFoundException("Impossible to update certificate with id = " + id + "Certificate doesn't exists.");
        }
        GiftCertificateEntity certificate;
        if (persistedCertificate.equals(updatedCertificate)) {
            return persistedCertificate;
        } else {
            certificateConverter(persistedCertificate, updatedCertificate);
            certificate = certificateDao.update(persistedCertificate);
        }
        return certificate;
    }

    private void certificateConverter(GiftCertificateEntity persistedCertificate, GiftCertificateEntity updatedCertificate) {
        String name = updatedCertificate.getName();
        String description = updatedCertificate.getDescription();
        BigDecimal price = updatedCertificate.getPrice();
        Integer validDays = updatedCertificate.getValidDays();
        List<Tag> tags = updatedCertificate.getTags();
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
        if(tags != null) {
            persistedCertificate.getTags().clear();
            persistedCertificate.getTags().addAll(tagsMapper(tags));
        }
    }
    private List<Tag> tagsMapper(List<Tag> tags) {
        tags.stream().filter(tag -> tagDao.getByName(tag.getName()) == null).forEach(tagDao::create);
            return tags.stream().map(tag -> tagDao.getByName(tag.getName())).collect(Collectors.toList());
    }
}

