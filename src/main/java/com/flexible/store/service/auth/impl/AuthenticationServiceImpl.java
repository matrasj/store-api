package com.flexible.store.service.auth.impl;

import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.entity.type.Role;
import com.flexible.store.factories.ConfirmationTokenFactory;
import com.flexible.store.service.auth.JwtTokenService;
import com.flexible.store.payload.auth.AuthenticationRequestPayload;
import com.flexible.store.payload.auth.AuthenticationResponsePayload;
import com.flexible.store.payload.auth.RegistrationRequestPayload;
import com.flexible.store.repository.useraccount.UserAccountRepository;
import com.flexible.store.service.auth.AuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthenticationResponsePayload register(RegistrationRequestPayload requestPayload) {
        var account = UserAccountEntity.builder()
                .firstname(requestPayload.getFirstname())
                .lastname(requestPayload.getLastname())
                .email(requestPayload.getEmail())
                .username(requestPayload.getEmail())
                .role(Role.CUSTOMER)
                .active(Boolean.FALSE)
                .password(this.passwordEncoder.encode(requestPayload.getPassword()))
                .build();
        UserAccountEntity savedUserAccount = this.userAccountRepository.save(account);
        var confirmationToken = ConfirmationTokenFactory.generateConfirmationToken(savedUserAccount.getId());
        return AuthenticationResponsePayload.builder()
                .confirmationToken(confirmationToken.getToken())
                .build();
    }

    @Override
    public AuthenticationResponsePayload authenticate(AuthenticationRequestPayload requestPayload) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestPayload.getUsername(),
                        requestPayload.getPassword()
                )
        );
        UserAccountEntity userAccount = this.userAccountRepository.findByEmail(requestPayload.getUsername())
                .orElseThrow(EntityNotFoundException::new);
        return AuthenticationResponsePayload.builder()
                .confirmationToken(this.jwtTokenService.generateJwtToken(userAccount))
                .build();
    }
}
