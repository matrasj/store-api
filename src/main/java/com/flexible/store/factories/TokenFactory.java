package com.flexible.store.factories;

import com.flexible.store.config.token.TokenPropertiesConfig;
import com.flexible.store.dto.confirmationtoken.ConfirmationTokenDto;
import com.flexible.store.dto.refreshtoken.RefreshTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenFactory {
    private final TokenPropertiesConfig tokenPropertiesConfig;

    public ConfirmationTokenDto generateConfirmationToken(Long userAccountId) {
        ConfirmationTokenDto confirmationTokenDto = new ConfirmationTokenDto();
        confirmationTokenDto.setToken(UUID.randomUUID().toString());
        confirmationTokenDto.setExpiresAt(LocalDateTime.now().plusMinutes(this.tokenPropertiesConfig.getConfirmationTokenExpirationTimeInMinutes()));
        confirmationTokenDto.setUserAccountId(userAccountId);
        return confirmationTokenDto;
    }

    public RefreshTokenDto generateRefreshToken(Long userAccountId) {
        RefreshTokenDto refreshToken = new RefreshTokenDto();
        refreshToken.setUserAccountId(userAccountId);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(this.tokenPropertiesConfig.getRefreshTokenExpirationTimeInDays()));
        return refreshToken;
    }

}
