package com.flexible.store.jwt;

public interface JwtTokenService {
    String extractUsernameFromJwtToken(String jwtToken);
}
