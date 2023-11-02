package com.flexible.store.payload.useraccount;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationEventPayload {
    private String email;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String username;
    private String confirmationToken;
}
