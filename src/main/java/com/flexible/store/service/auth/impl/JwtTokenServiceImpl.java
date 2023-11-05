package com.flexible.store.service.auth.impl;

import com.flexible.store.config.token.TokenPropertiesConfig;
import com.flexible.store.service.auth.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {
    private final TokenPropertiesConfig tokenPropertiesConfig;

    @Override
    public String extractUsernameFromJwtToken(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public String generateJwtToken(UserDetails details) {
        return generateJwtToken(new HashMap<>(), details);
    }
    
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = this.extractUsernameFromJwtToken(jwtToken);
        return Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    public String generateJwtToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.tokenPropertiesConfig.getJwtTokenExpirationTimeInMinutes() * 1000 * 60))
                .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String jwtToken) {
        return this.extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return this.extractClaim(jwtToken, Claims::getExpiration);
    }

    private Claims extractClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.tokenPropertiesConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
