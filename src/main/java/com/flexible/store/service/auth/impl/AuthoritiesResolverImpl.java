package com.flexible.store.service.auth.impl;

import com.flexible.store.entity.type.Role;
import com.flexible.store.service.auth.AuthoritiesResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuthoritiesResolverImpl implements AuthoritiesResolver {
    @Override
    public boolean hasRole(Role role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream()
                .anyMatch(authority -> (String.format("ROLE_%s", role.name())).equals(authority.getAuthority()));
    }

    @Override
    public boolean hasOneOfRoles(List<Role> roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (Role role : roles) {
            String roleAuthority = String.format("ROLE_%s", role.name());
            if (authorities.stream().anyMatch(authority -> roleAuthority.equals(authority.getAuthority()))) {
                return true;
            }
        } return false;
    }

    @Override
    public String obtainUsernameFromCurrentAuthenticationContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }
}
