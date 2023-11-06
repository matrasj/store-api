package com.flexible.store.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.flexible.store.entity.type.Permission.*;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(List.of(
            COUNTRY_CREATE, COUNTRY_READ, COUNTRY_UPDATE, COUNTRY_DELETE
    )),
    MODERATOR(List.of(
            COUNTRY_CREATE, COUNTRY_READ, COUNTRY_UPDATE, COUNTRY_DELETE
    )),
    EMPLOYEE(List.of(
            COUNTRY_CREATE, COUNTRY_READ, COUNTRY_UPDATE, COUNTRY_DELETE
    )),
    CUSTOMER(List.of(
            COUNTRY_READ
    ));

    private final List<Permission> permissions;
}
