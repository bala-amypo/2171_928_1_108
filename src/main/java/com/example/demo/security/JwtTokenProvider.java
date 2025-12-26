package com.example.demo.security;

import org.springframework.security.core.Authentication;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {

    private final String secretKey;
    private final long validityInMs;
    private final boolean enabled;

    public JwtTokenProvider(String secretKey, long validityInMs, boolean enabled) {
        this.secretKey = secretKey;
        this.validityInMs = validityInMs;
        this.enabled = enabled;
    }

    public String generateToken(Authentication authentication, Long userId, String role) {
        String raw =
                authentication.getName() + "|" +
                userId + "|" +
                role + "|" +
                System.currentTimeMillis();

        return Base64.getEncoder().encodeToString(raw.getBytes());
    }

    public String getUsernameFromToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            return decoded.split("\\|")[0];
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Base64.getDecoder().decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Object> getAllClaims(String token) {
        Map<String, Object> claims = new HashMap<>();
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            String[] parts = decoded.split("\\|");

            claims.put("email", parts[0]);
            claims.put("userId", Long.parseLong(parts[1]));
            claims.put("role", parts[2]);
        } catch (Exception ignored) {
        }
        return claims;
    }
}
