package com.epam.mjc.service.security.jwt;

import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDto user;
        try {
            user = userService.findUserByEmail(email);

        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }

        return JwtUserFactory.create(user);
    }
}
