package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.service.UserService;
import com.epam.mjc.web.linkbuilder.UserLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                        @RequestParam(name = "page", defaultValue = "1") Integer pageNumber ) {
        List<UserDto> users = userService.getAllUsers(size, pageNumber);
        int usersTableSize = userService.countUsers();

        return new PageDto<>(userLinkBuilder.addLinksToList(users), usersTableSize);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and authentication.principal.id == #id)")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {

        return userLinkBuilder.addLinksToDto(userService.updateUser(id, userDto));
    }
}

