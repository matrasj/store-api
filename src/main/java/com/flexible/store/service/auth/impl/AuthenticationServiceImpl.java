package com.flexible.store.service.auth.impl;

import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.entity.type.Role;
import com.flexible.store.factories.ConfirmationTokenFactory;
import com.flexible.store.kafka.KafkaProducer;
import com.flexible.store.mapper.useraccount.UserAccountMapper;
import com.flexible.store.payload.useraccount.RegistrationEventPayload;
import com.flexible.store.service.auth.JwtTokenService;
import com.flexible.store.payload.auth.AuthenticationRequestPayload;
import com.flexible.store.payload.auth.AuthenticationResponsePayload;
import com.flexible.store.payload.auth.RegistrationRequestPayload;
import com.flexible.store.service.auth.AuthenticationService;
import com.flexible.store.service.useraccount.ConfirmationTokenService;
import com.flexible.store.service.useraccount.UserAccountService;
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
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountMapper userAccountMapper;
    private final JwtTokenService jwtTokenService;
    private final KafkaProducer kafkaProducer;
    private final ConfirmationTokenService confirmationTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthenticationResponsePayload register(RegistrationRequestPayload requestPayload) {
        var account = new UserAccountEntity();
        account.setFirstname(requestPayload.getFirstname());
        account.setLastname(requestPayload.getLastname());
        account.setEmail(requestPayload.getEmail());
        account.setUsername(requestPayload.getEmail());
        account.setRole(Role.CUSTOMER);
        account.setActive(Boolean.FALSE);
        account.setPassword(this.passwordEncoder.encode(requestPayload.getPassword()));

        UserAccountEntity savedUserAccount = this.userAccountService.save(this.userAccountMapper.toDto(account));
        ConfirmationTokenEntity savedConfirmationToken
                = this.confirmationTokenService.save(ConfirmationTokenFactory.generateConfirmationToken(savedUserAccount.getId()));

        this.kafkaProducer.publishMessageAboutRegistration(
                RegistrationEventPayload.builder()
                .confirmationToken(savedConfirmationToken.getToken())
                .email(savedUserAccount.getEmail())
                .firstname(savedUserAccount.getFirstname())
                .lastname(savedUserAccount.getLastname())
                .phoneNumber(savedUserAccount.getPhoneNumber())
                .username(savedUserAccount.getEmail())
                .build()
        );
        return AuthenticationResponsePayload.builder()
                .confirmationToken(savedConfirmationToken.getToken())
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
        UserAccountEntity userAccount = this.userAccountService.findByEmail(requestPayload.getUsername())
                .orElseThrow(EntityNotFoundException::new);
        return AuthenticationResponsePayload.builder()
                .confirmationToken(this.jwtTokenService.generateJwtToken(userAccount))
                .build();
    }
}
