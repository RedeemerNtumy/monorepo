package com.dennis.authentication_service.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    public Map<String, String> generateToken(String username, String role) {

        return jwtService.generateToken(username, role);
    }

    public void validateToken(String token) {

        jwtService.validateToken(token);
    }


    public String extractRole(String token) {
        return jwtService.extractRole(token);
    }
}
