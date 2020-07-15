package com.epam.mjc.service.mapper;

import com.epam.mjc.dao.entity.GiftCertificateDto;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GiftCertificateMapper  {
    @Autowired
    private ModelMapper mapper;

    public GiftCertificateEntity toEntity(GiftCertificateDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, GiftCertificateEntity.class);
    }

    public GiftCertificateDto toDto(GiftCertificateEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, GiftCertificateDto.class);
    }
}
