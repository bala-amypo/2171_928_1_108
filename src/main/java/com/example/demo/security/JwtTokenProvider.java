package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {

    private final String secretKey;
    private final long validityInMilliseconds;
    private final boolean encodeSecret;

    public JwtTokenProvider(String secretKey, long validityInMilliseconds, boolean encodeSecret) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
        this.encodeSecret = encodeSecret;
    }

    public String generateToken(Authentication authentication, Long userId, String role) {
        String email = authentication.getName();

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("role", role);
        claims.put("email", email);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaims(token).get("email", String.class);
    }

    public Map<String, Object> getAllClaims(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            Map<String, Object> map = new HashMap<>();
            map.put("userId", claims.get("userId"));
            map.put("role", claims.get("role"));
            map.put("email", claims.get("email"));
            return map;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
