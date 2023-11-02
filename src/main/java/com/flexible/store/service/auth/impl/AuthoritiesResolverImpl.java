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
    public boolean hasRoleAndIsActive(Role role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        boolean enabled = ((UserDetails) principal).isEnabled();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return enabled && authorities.stream()
                .anyMatch(authority -> (String.format("ROLE_%s", role.name())).equals(authority.getAuthority()));
    }

    @Override
    public boolean hasOneOfRolesAndIsActive(List<Role> roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        boolean enabled = ((UserDetails) principal).isEnabled();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (Role role : roles) {
            String roleAuthority = String.format("ROLE_%s", role.name());
            if (enabled && authorities.stream().anyMatch(authority -> roleAuthority.equals(authority.getAuthority()))) {
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
