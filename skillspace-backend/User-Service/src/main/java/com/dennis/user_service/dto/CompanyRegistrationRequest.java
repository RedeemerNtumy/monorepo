package com.dennis.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegistrationRequest {
    private String name;
    private String email;
    private String password;
    private String contact;
    private String website;
    private MultipartFile certificate;
    private MultipartFile logo;
}
