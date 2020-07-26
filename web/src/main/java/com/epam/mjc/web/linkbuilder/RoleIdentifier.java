package com.epam.mjc.web.linkbuilder;

import com.epam.mjc.dao.dto.RoleDto;
import com.epam.mjc.service.security.jwt.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class RoleIdentifier {
    public static List<RoleDto> getRoles() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities().stream().map(GrantedAuthority::getAuthority).map(RoleDto::new).collect(Collectors.toList());
    }
    public static JwtUser getSessionId() {
        return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
