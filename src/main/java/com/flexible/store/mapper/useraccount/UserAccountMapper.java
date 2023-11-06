package com.flexible.store.mapper.useraccount;

import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.entity.type.Role;
import com.flexible.store.payload.auth.RegistrationRequestPayload;

public class UserAccountMapper {

    public static UserAccountEntity fromRequestPayloadToEntity(RegistrationRequestPayload requestPayload) {
        return UserAccountEntity.builder()
                .firstname(requestPayload.getFirstname())
                .lastname(requestPayload.getLastname())
                .email(requestPayload.getEmail())
                .username(requestPayload.getUsername())
                .phoneNumber(requestPayload.getPhoneNumber())
                .role(Role.CUSTOMER)
                .active(Boolean.FALSE)
                .removed(Boolean.FALSE)
                .build();
    }
}
