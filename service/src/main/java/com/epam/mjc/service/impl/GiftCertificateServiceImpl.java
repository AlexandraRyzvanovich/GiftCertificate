package com.epam.mjc.service.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.TagEntity;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.service.exception.EntityAlreadyExistsException;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.mapper.GiftCertificateMapper;
import com.epam.mjc.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao certificateDao;
    private final TagDao tagDao;
    private final GiftCertificateMapper certificateMapper;
    private final TagMapper tagMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, TagDao tagDao, GiftCertificateMapper mapper, TagMapper tagMapper) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.certificateMapper = mapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public GiftCertificateDto getCertificateById(Long id) {
        return certificateDao.getById(id).map(certificateMapper::toDto)
                .orElseThrow(()-> new NotFoundException("Certificate with id " + id + " not found"));
    }

    @Override
    public List<GiftCertificateDto> getCertificates(SearchParams searchParams, Integer size, Integer pageNumber) {
        return (certificateDao.getAll(searchParams, size, pageNumber)).stream().map(certificateMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto createCertificate(GiftCertificateDto certificate) {
        Optional<GiftCertificateEntity> certificateEntity = certificateDao.getByName(certificate.getName());
        if(certificateEntity.isPresent()) {
            throw new EntityAlreadyExistsException("Certificate with such name " + certificate.getName() + " already exists");
        }
        GiftCertificateEntity certificateToBeCreated = new GiftCertificateEntity(certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getValidDays());
        certificateToBeCreated.setCreationDate(LocalDateTime.now());
        if(certificate.getTags()!=null) {
            List<TagEntity> tags = certificate.getTags().stream().map(tagMapper::toEntity).collect(Collectors.toList());
            certificateToBeCreated.setTagEntities(tagsMapper(tags));
        }
        Long createdId = certificateDao.create(certificateToBeCreated);
        if (createdId == null) {
            throw new IncorrectParamsException("Exception occurred while executing create certificate query");
        }

        return getCertificateById(createdId);
    }

    @Override
    public GiftCertificateDto updateCertificate(Long id, GiftCertificateDto updatedCertificate) {
        GiftCertificateEntity persistedCertificateEntity = certificateDao.getById(id)
                .orElseThrow(() -> new NotFoundException("Impossible to update certificate with id = " + id + " Certificate doesn't exists."));
        
        GiftCertificateEntity updatedCertificateEntity = certificateMapper.toEntity(updatedCertificate);
        GiftCertificateEntity certificate;
        if (persistedCertificateEntity.equals(updatedCertificateEntity)) {
            return certificateMapper.toDto(persistedCertificateEntity);
        } else {
            certificateConverter(persistedCertificateEntity, updatedCertificateEntity);
            persistedCertificateEntity.setModificationDate(LocalDateTime.now());
            certificate = certificateDao.update(persistedCertificateEntity);
        }
        return certificateMapper.toDto(certificate);
    }

    @Override
    public int countCertificates(SearchParams params) {
        return certificateDao.countCertificates(params);
    }

    private void certificateConverter(GiftCertificateEntity persistedCertificate, GiftCertificateEntity updatedCertificate) {
        String name = updatedCertificate.getName();
        String description = updatedCertificate.getDescription();
        BigDecimal price = updatedCertificate.getPrice();
        Integer validDays = updatedCertificate.getValidDays();
        List<TagEntity> tagEntities = updatedCertificate.getTagEntities();
        if (name != null && name != "") {
            persistedCertificate.setName(name);
        }
        if (description != null && description != "") {
            persistedCertificate.setDescription(description);
        }
        if (price != null) {
            persistedCertificate.setPrice(price);
        }
        if (validDays != null) {
            persistedCertificate.setValidDays(validDays);
        }
        if(tagEntities != null) {
            persistedCertificate.getTagEntities().clear();
            persistedCertificate.getTagEntities().addAll(tagsMapper(tagEntities));
        }
    }

    private List<TagEntity> tagsMapper(List<TagEntity> tagEntities) {
        tagEntities.stream().filter(tag -> !tagDao.getByName(tag.getName()).isPresent()).forEach(tagDao::create);
            return tagEntities.stream().map(tag -> tagDao.getByName(tag.getName()).get()).collect(Collectors.toList());
    }
}

