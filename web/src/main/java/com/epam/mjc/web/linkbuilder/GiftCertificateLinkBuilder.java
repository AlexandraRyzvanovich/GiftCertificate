package com.epam.mjc.web.linkbuilder;

import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.dao.dto.RoleDto;
import com.epam.mjc.web.controller.CertificateController;
import com.epam.mjc.web.controller.OrderController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateLinkBuilder implements LinkBuilder<GiftCertificateDto> {

    @Override
    public CollectionModel<GiftCertificateDto> addLinksToList(List<GiftCertificateDto> giftCertificateDtoList) {
        List<RoleDto> roles =  RoleIdentifier.getRoles();
        giftCertificateDtoList.forEach(c -> addLinksToDto(c, roles));
        
        return CollectionModel.of(
                giftCertificateDtoList,
                linkTo(methodOn(CertificateController.class).getAllCertificates(null, null, null, null, null)).withRel("getCertificates").expand());
    }

    @Override
    public GiftCertificateDto addLinksToDto(GiftCertificateDto giftCertificateDto) {

        return addLinksToDto(giftCertificateDto, RoleIdentifier.getRoles());
    }

    @Override
    public GiftCertificateDto addLinksToDto(GiftCertificateDto giftCertificateDto, List<RoleDto> listRoles) {
        giftCertificateDto.add(linkTo(methodOn(CertificateController.class).getCertificateById(giftCertificateDto.getId())).withSelfRel());
        if (listRoles.stream().anyMatch(a -> a.getName().equalsIgnoreCase("ROLE_ADMIN"))) {
            giftCertificateDto.add(linkTo(methodOn(CertificateController.class).updateCertificate(giftCertificateDto.getId(), giftCertificateDto)).withRel("updateCertificate"));
            giftCertificateDto.add(linkTo(methodOn(CertificateController.class).createCertificate(giftCertificateDto)).withRel("createCertificate"));
        }
        if (listRoles.stream().anyMatch(a -> a.getName().equalsIgnoreCase("ROLE_USER"))) {
            giftCertificateDto.add(linkTo(methodOn(OrderController.class).createOrder(null, RoleIdentifier.getSessionId().getId())).withRel("createOrder"));
        }
        return giftCertificateDto;
    }
}
