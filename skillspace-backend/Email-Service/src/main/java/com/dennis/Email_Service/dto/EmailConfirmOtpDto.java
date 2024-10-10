package com.dennis.Email_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EmailConfirmOtpDto {
    private String email;
    private String otp;
}
