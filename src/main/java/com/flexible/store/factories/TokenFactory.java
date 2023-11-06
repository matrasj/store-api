package com.flexible.store.factories;

import com.flexible.store.config.token.TokenPropertiesConfig;
import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenFactory {
    private final TokenPropertiesConfig tokenPropertiesConfig;

    public ConfirmationTokenEntity generateConfirmationToken(Long userAccountId) {
        return ConfirmationTokenEntity.builder()
                .removed(Boolean.FALSE)
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusMinutes(this.tokenPropertiesConfig.getConfirmationTokenExpirationTimeInMinutes()))
                .userAccountId(userAccountId)
                .build();
    }

    public RefreshTokenEntity generateRefreshToken(Long userAccountId) {
        return RefreshTokenEntity.builder()
                .removed(Boolean.FALSE)
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusDays(this.tokenPropertiesConfig.getRefreshTokenExpirationTimeInDays()))
                .userAccountId(userAccountId)
                .build();
    }

}
