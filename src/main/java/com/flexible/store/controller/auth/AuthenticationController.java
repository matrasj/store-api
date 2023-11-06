package com.flexible.store.controller.auth;

import com.flexible.store.entity.RefreshTokenEntity;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.exception.common.EntityNotFoundException;
import com.flexible.store.payload.auth.*;
import com.flexible.store.repository.refreshtoken.RefreshTokenRepository;
import com.flexible.store.repository.refreshtoken.UserAccountRepository;
import com.flexible.store.service.auth.AuthenticationService;
import com.flexible.store.service.auth.JwtTokenService;
import com.flexible.store.service.refreshtoken.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserAccountRepository userAccountRepository;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponsePayload> register(@RequestBody RegistrationRequestPayload requestPayload) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.authenticationService.register(requestPayload));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponsePayload> authenticate(@RequestBody AuthenticationRequestPayload requestPayload) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.authenticationService.authenticate(requestPayload));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponsePayload> refreshToken(@RequestBody RefreshTokenRequestPayload tokenRequestPayload) {
        RefreshTokenEntity refreshTokenEntity
                = this.refreshTokenRepository.findByToken(tokenRequestPayload.getRefreshToken()).orElseThrow(EntityNotFoundException::new);
        this.refreshTokenService.verifyExpiration(refreshTokenEntity);
        UserAccountEntity userAccount = this.userAccountRepository.findById(refreshTokenEntity.getUserAccountId()).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK)
                .body(AuthenticationResponsePayload
                        .builder()
                        .jwtToken(this.jwtTokenService.generateJwtToken(userAccount))
                        .refreshToken(refreshTokenEntity.getToken())
                        .build());
    }

    @GetMapping("/confirmation/token/{tokenId}")
    public ResponseEntity<String> confirmAccount(@PathVariable Long tokenId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.authenticationService.confirmToken(tokenId));
    }
}
