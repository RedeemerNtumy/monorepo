package com.dennis.authentication_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    // Add role to token claims
    public Map<String, String> generateToken(String userName, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);  // Add role to claims

        String token = createToken(claims, userName);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // You can later extract the role from token claims when needed
    public String extractRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
        return claims.get("role", String.class);  // Extract role
    }

    public void validateToken(final String token) {
        Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }
}