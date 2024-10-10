package com.dennis.authentication_service.modle;

import com.dennis.authentication_service.dto.AuthRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Role role;  // Add role field

    public CustomUserDetails(AuthRequest userCredential, Role role) {
        this.username = userCredential.getEmail();
        this.password = userCredential.getPassword();
        this.role = role;  // Set role
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));  // Use role as an authority
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole() {
        return role;
    }
}