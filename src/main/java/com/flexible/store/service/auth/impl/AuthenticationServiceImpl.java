package com.flexible.store.service.auth.impl;

import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.entity.RefreshTokenEntity;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.exception.auth.InvalidCredentialsException;
import com.flexible.store.exception.common.EntityNotFoundException;
import com.flexible.store.factories.TokenFactory;
import com.flexible.store.kafka.KafkaProducer;
import com.flexible.store.mapper.useraccount.UserAccountMapper;
import com.flexible.store.payload.auth.AuthenticationResponsePayload;
import com.flexible.store.payload.useraccount.RegistrationEventPayload;
import com.flexible.store.repository.confirmationtoken.ConfirmationTokenRepository;
import com.flexible.store.repository.refreshtoken.RefreshTokenRepository;
import com.flexible.store.repository.refreshtoken.UserAccountRepository;
import com.flexible.store.service.auth.JwtTokenService;
import com.flexible.store.payload.auth.AuthenticationRequestPayload;
import com.flexible.store.payload.auth.RegistrationResponsePayload;
import com.flexible.store.payload.auth.RegistrationRequestPayload;
import com.flexible.store.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final KafkaProducer kafkaProducer;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenFactory tokenFactory;

    @Override
    @Transactional
    public RegistrationResponsePayload register(RegistrationRequestPayload requestPayload) {
        UserAccountEntity userAccount = UserAccountMapper.fromRequestPayloadToEntity(requestPayload);
        userAccount.setPassword(this.passwordEncoder.encode(requestPayload.getPassword()));
        UserAccountEntity savedUserAccount = this.userAccountRepository.save(userAccount);

        ConfirmationTokenEntity savedConfirmationToken = this.confirmationTokenRepository.save(this.tokenFactory.generateConfirmationToken(savedUserAccount.getId()));
        this.kafkaProducer.publishMessageAboutRegistration(this.buildRegistrationPayload(savedUserAccount, savedConfirmationToken));
        return RegistrationResponsePayload.builder()
                .confirmationToken(savedConfirmationToken.getToken())
                .build();
    }

    @Override
    public AuthenticationResponsePayload authenticate(AuthenticationRequestPayload requestPayload) {
        UserAccountEntity userAccount = this.userAccountRepository.findByEmail(requestPayload.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("No account exists with email: " + requestPayload.getUsername()));
        Authentication authentication = null;
        try {
             authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestPayload.getUsername(),
                            requestPayload.getPassword()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new InvalidCredentialsException("Invalid password for email: " + requestPayload.getUsername());
        }

        if (authentication.isAuthenticated()) {
            RefreshTokenEntity savedRefreshToken
                    = this.refreshTokenRepository.save(this.tokenFactory.generateRefreshToken(userAccount.getId()));
            return AuthenticationResponsePayload.builder()
                    .jwtToken(this.jwtTokenService.generateJwtToken(userAccount))
                    .refreshToken(savedRefreshToken.getToken())
                    .build();
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    @Transactional
    public String confirmToken(Long confirmationTokenId) {
        ConfirmationTokenEntity confirmationToken
                = this.confirmationTokenRepository.findById(confirmationTokenId).orElseThrow(EntityNotFoundException::new);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Account is already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token already expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        UserAccountEntity userAccount = this.userAccountRepository.findById(confirmationToken.getUserAccountId())
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
}
