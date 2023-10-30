package com.flexible.store.service.auth;

import com.flexible.store.entity.type.Role;

import java.util.List;

public interface AuthoritiesResolver {
    boolean hasRole(Role role);
    boolean hasOneOfRoles(List<Role> roles);
    String obtainUsernameFromCurrentAuthenticationContext();
}
