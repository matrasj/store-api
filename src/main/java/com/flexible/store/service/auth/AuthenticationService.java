package com.flexible.store.service.auth;

import com.flexible.store.entity.type.Role;
import com.flexible.store.payload.auth.AuthenticationRequestPayload;
import com.flexible.store.payload.auth.AuthenticationResponsePayload;
import com.flexible.store.payload.auth.RegistrationRequestPayload;

public interface AuthenticationService {

    AuthenticationResponsePayload register(RegistrationRequestPayload requestPayload);

    AuthenticationResponsePayload authenticate(AuthenticationRequestPayload requestPayload);
}
