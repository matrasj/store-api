package com.flexible.store.service.auth.impl;

import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.entity.type.Role;
import com.flexible.store.factories.ConfirmationTokenFactory;
import com.flexible.store.kafkaproducer.KafkaProducer;
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

import java.time.LocalDateTime;

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
        this.setAccountProperties(requestPayload, account);

        UserAccountEntity savedUserAccount = this.userAccountService.save(this.userAccountMapper.toDto(account));
        ConfirmationTokenEntity savedConfirmationToken
                = this.confirmationTokenService.save(ConfirmationTokenFactory.generateConfirmationToken(savedUserAccount.getId()));

        this.kafkaProducer.publishMessageAboutRegistration(this.buildRegistrationPayload(savedUserAccount, savedConfirmationToken));
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

    @Override
    @Transactional
    public String confirmToken(Long confirmationTokenId) {
        ConfirmationTokenEntity confirmationToken
                = this.confirmationTokenService.getById(confirmationTokenId).orElseThrow(EntityNotFoundException::new);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Account is already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token already expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        UserAccountEntity userAccount = this.userAccountService.getById(confirmationToken.getUserAccountId())
                .orElseThrow(EntityNotFoundException::new);
        userAccount.setActive(Boolean.TRUE);
        return "Successfully confirmed";
    }

    private RegistrationEventPayload buildRegistrationPayload(UserAccountEntity savedUserAccount, ConfirmationTokenEntity savedConfirmationToken) {
        return RegistrationEventPayload.builder()
                .email(savedUserAccount.getEmail())
                .firstname(savedUserAccount.getFirstname())
                .lastname(savedUserAccount.getLastname())
                .confirmationTokenId(savedConfirmationToken.getId())
                .build();
    }

    private void setAccountProperties(RegistrationRequestPayload requestPayload, UserAccountEntity account) {
        account.setFirstname(requestPayload.getFirstname());
        account.setLastname(requestPayload.getLastname());
        account.setEmail(requestPayload.getEmail());
        account.setUsername(requestPayload.getEmail());
        account.setPhoneNumber(requestPayload.getPhoneNumber());
        account.setRole(Role.CUSTOMER);
        account.setActive(Boolean.FALSE);
        account.setPassword(this.passwordEncoder.encode(requestPayload.getPassword()));
    }
}
