package com.epam.mjc.web.linkbuilder;

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
        addSelfLink(userDto);
        if(RoleIdentifier.isAdmin()) {
            userDto.add(linkTo(methodOn(UserController.class).getAllUsers(null, null)).withRel("/users"));

        }

        return userDto;
    }
    private void addSelfLink(UserDto userDto) {
        userDto.add(linkTo(methodOn(UserController.class).getUserById(userDto.getId())).withSelfRel());
    }
}
