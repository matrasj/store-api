package com.flexible.store.service.refreshtoken.impl;

import com.flexible.store.entity.RefreshTokenEntity;
import com.flexible.store.repository.refreshtoken.RefreshTokenRepository;
import com.flexible.store.service.refreshtoken.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            this.refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
}
