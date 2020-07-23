package com.epam.mjc.web.linkbuilder;

import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.web.controller.CertificateController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateLinkBuilder implements LinkBuilder<GiftCertificateDto> {


    @Override
    public CollectionModel<GiftCertificateDto> addLinksToList(List<GiftCertificateDto> giftCertificateDtoList) {
        for (GiftCertificateDto certificate : giftCertificateDtoList) {
            addLinksToDto(certificate);
        }
        return CollectionModel.of(
                giftCertificateDtoList,
                linkTo(methodOn(CertificateController.class).getAllCertificates(null, null, null, null, null)).withRel("getCertificates"));
    }

    @Override
    public GiftCertificateDto addLinksToDto(GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.add(linkTo(methodOn(CertificateController.class).getCertificateById(giftCertificateDto.getId())).withSelfRel());
        if (RoleIdentifier.isAdmin()) {
            giftCertificateDto.add(linkTo(methodOn(CertificateController.class).updateCertificate(giftCertificateDto.getId(), giftCertificateDto)).withRel("updateCertificate"));
            giftCertificateDto.add(linkTo(methodOn(CertificateController.class).createCertificate(giftCertificateDto)).withRel("createCertificate"));
        }
        return giftCertificateDto;
    }
}
