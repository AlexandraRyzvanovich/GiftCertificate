package com.epam.mjc.web.linkbuilder;

import com.epam.mjc.dao.dto.RoleDto;
import com.epam.mjc.dao.entity.Identifiable;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface LinkBuilder<T extends Identifiable> {
   T addLinksToDto(T dto);
    CollectionModel<T> addLinksToList(List<T> dto);

    T addLinksToDto(T dto, List<RoleDto> listRoles);
}
