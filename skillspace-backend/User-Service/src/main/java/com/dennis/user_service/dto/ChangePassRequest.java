package com.dennis.user_service.dto;

import lombok.Data;

@Data
public class ChangePassRequest {
    private String email;
    private String newPassword;
}
