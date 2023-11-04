package com.flexible.store.payload.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegistrationRequestPayload {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String phoneNumber;
}
