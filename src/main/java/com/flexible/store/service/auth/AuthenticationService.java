package com.flexible.store.service.auth;

import com.flexible.store.payload.auth.AuthenticationRequestPayload;
import com.flexible.store.payload.auth.AuthenticationResponsePayload;
import com.flexible.store.payload.auth.RegistrationResponsePayload;
import com.flexible.store.payload.auth.RegistrationRequestPayload;

public interface AuthenticationService {
    RegistrationResponsePayload register(RegistrationRequestPayload requestPayload);
    AuthenticationResponsePayload authenticate(AuthenticationRequestPayload requestPayload);
    String confirmToken(Long confirmationTokenId);

}
