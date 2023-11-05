package com.flexible.store.config.token;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TokenPropertiesConfig {
    @Value("${token.jwt-token-expiration-time-minutes}")
    private Long jwtTokenExpirationTimeInMinutes;
    @Value("${token.refresh-token-expiration-time-days}")
    private Long refreshTokenExpirationTimeInDays;
    @Value("${token.confirmation-token-expiration-time-minutes}")
    private Long confirmationTokenExpirationTimeInMinutes;
    @Value("${token.jwt-token-secret-key}")
    private String secretKey;
}
