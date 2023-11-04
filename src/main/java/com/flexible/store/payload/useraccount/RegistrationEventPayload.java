package com.flexible.store.payload.useraccount;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationEventPayload {
    private String email;
    private String firstname;
    private String lastname;
    private Long confirmationTokenId;
}
