package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.service.UserService;
import com.epam.mjc.web.linkbuilder.UserLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        UserDto user = userLinkBuilder.addLinksToDto(userService.getById(id));

        return user;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public PageDto<UserDto> getAllUsers(@RequestParam(name = "size", defaultValue = "5") Integer size,
                                        @RequestParam(name = "number", defaultValue = "1") Integer pageNumber ) {

        return userService.getAllUsers(size, pageNumber);
    }

    @PutMapping("/{id}")
    @PreAuthorize("authentication.principal.id == #id")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {

        return userService.updateUser(id, userDto);
    }
}

