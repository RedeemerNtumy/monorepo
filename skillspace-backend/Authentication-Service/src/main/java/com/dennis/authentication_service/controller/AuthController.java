package com.dennis.authentication_service.controller;


import com.dennis.authentication_service.dto.AuthRequest;
import com.dennis.authentication_service.modle.CustomUserDetails;
import com.dennis.authentication_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public Map<String, String> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            // Fetch user details (including role)
            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
            String role = userDetails.getRole().name();  // Get the role

            // Pass the role to JwtService
            return service.generateToken(authRequest.getEmail(), role);  // Include role in token
        } else {
            throw new RuntimeException("Unauthorized access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);

        // Extract role from token
        String role = service.extractRole(token);
        return "Token is valid for role: " + role;
    }
}
