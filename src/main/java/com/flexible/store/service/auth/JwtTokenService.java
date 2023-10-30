package com.flexible.store.service.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {
    String extractUsernameFromJwtToken(String jwtToken);
    boolean isTokenValid(String jwtToken, UserDetails userDetails);
    String generateJwtToken(UserDetails details);
}
