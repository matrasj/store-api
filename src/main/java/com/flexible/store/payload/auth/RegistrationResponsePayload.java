package com.flexible.store.payload.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegistrationResponsePayload {
    private String confirmationToken;
}
