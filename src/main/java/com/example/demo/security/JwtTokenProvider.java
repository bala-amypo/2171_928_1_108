package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private String secret;
    private long validityInMilliseconds;
    private boolean enableRefresh;

    public JwtTokenProvider(String secret, long validityInMilliseconds, boolean enableRefresh) {
        this.secret = secret;
        this.validityInMilliseconds = validityInMilliseconds;
        this.enableRefresh = enableRefresh;
    }

    public String generateToken(org.springframework.security.core.Authentication auth, long userId, String role) {
        // Implementation
        return "token";
    }

    public boolean validateToken(String token) {
        // Implementation
        return true;
    }

    public Object getAllClaims(String token) {
        // Implementation
        return null;
    }
}
