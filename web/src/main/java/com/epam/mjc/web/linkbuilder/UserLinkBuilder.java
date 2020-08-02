package com.epam.mjc.web.linkbuilder;

import com.epam.mjc.dao.dto.RoleDto;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.web.controller.UserController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserLinkBuilder implements LinkBuilder<UserDto> {

    @Override
    public CollectionModel<UserDto> addLinksToList(List<UserDto> userDtoList) {
        for (UserDto user: userDtoList) {
            addLinksToDto(user);
        }
        return CollectionModel.of(
                userDtoList);
    }
    @Override
    public UserDto addLinksToDto(UserDto userDto) {

        return addLinksToDto(userDto, RoleIdentifier.getRoles());
    }

    @Override
    public UserDto addLinksToDto(UserDto userDto, List<RoleDto> listRoles ) {
        userDto.add(linkTo(methodOn(UserController.class).getUserById(userDto.getId())).withSelfRel());
        if (listRoles.stream().anyMatch(a -> a.getName().equalsIgnoreCase("ROLE_ADMIN"))) {
            userDto.add(linkTo(methodOn(UserController.class).getAllUsers(null, null)).withRel("getUsers"));
        }
        userDto.add(linkTo(methodOn(UserController.class).updateUser(userDto.getId(), userDto)).withRel("updateUser"));

        return userDto;
    }
}
