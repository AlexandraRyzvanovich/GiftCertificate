package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.service.UserService;
import com.epam.mjc.web.linkbuilder.UserLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Autowired
    private UserLinkBuilder userLinkBuilder;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id" )
    public UserDto getUserById(@PathVariable("id") long id) {

        return userLinkBuilder.addLinksToDto(userService.getById(id));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public PageDto<UserDto> getAllUsers(@RequestParam(name = "size", defaultValue = "5") Integer size,
                                        @RequestParam(name = "number", defaultValue = "1") Integer pageNumber ) {
        PageDto<UserDto> users = userService.getAllUsers(size, pageNumber);

        userLinkBuilder.addLinksToList(new ArrayList<>(users.getItems().getContent()));

        return users;
    }

    @PutMapping("/{id}")
    @PreAuthorize("authentication.principal.id == #id")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {

        return userLinkBuilder.addLinksToDto(userService.updateUser(id, userDto));
    }
}

