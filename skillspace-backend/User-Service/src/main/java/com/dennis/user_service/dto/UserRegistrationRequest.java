package com.dennis.user_service.dto;

import com.dennis.user_service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
    private String contact;
    private Role role;
}
