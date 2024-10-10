package com.dennis.authentication_service.service;

import com.dennis.authentication_service.dto.AuthRequest;
import com.dennis.authentication_service.modle.AuthResponse;
import com.dennis.authentication_service.modle.CustomUserDetails;
import com.dennis.authentication_service.modle.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://USER-SERVICE/api/users/email/";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String url = UriComponentsBuilder.fromHttpUrl(USER_SERVICE_URL + username).toUriString();

        try {
            AuthRequest userDto = restTemplate.getForObject(url, AuthRequest.class);

            // Fetch user details along with the role
            if (userDto != null) {
                // Assume the AuthResponse has a field for the user's role (modify if necessary)
                Role userRole = restTemplate.getForObject("http://USER-SERVICE/api/users/role/" + username, Role.class);  // Add role fetching here
                return new CustomUserDetails(userDto, userRole);  // Pass the role
            } else {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with email: " + username, e);
        }
    }
}