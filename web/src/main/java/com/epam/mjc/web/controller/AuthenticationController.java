package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.AuthenticationRequestDto;
import com.epam.mjc.dao.dto.UserDto;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManagerBean;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManagerBean = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {

            String username = requestDto.getUsername();
            authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            UserDto user = userService.findUserByEmail(username);

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            response.put("roles", user.getRoles());

            return ResponseEntity.ok(response);
    }
    @PostMapping("/signUp")
    public UserDto register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }
}