package com.flexible.store.service.auth;

import com.flexible.store.entity.type.Role;

import java.util.List;

public interface AuthoritiesResolver {
    boolean hasRoleAndIsActive(Role role);
    boolean hasOneOfRolesAndIsActive(List<Role> roles);
    String obtainUsernameFromCurrentAuthenticationContext();
}
