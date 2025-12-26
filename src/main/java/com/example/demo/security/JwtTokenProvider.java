package com.example.demo.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String getUsernameFromToken(Claims claims) {
        return (String) claims.get("sub");
    }
}
