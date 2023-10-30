package com.flexible.store.payload.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequestPayload {
    private String username;
    private String password;
}
