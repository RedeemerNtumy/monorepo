package com.dennis.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfileResponse {
    private String name;
    private String email;
    private String contact;
    private String website;
    private String registrationCertificate;  // Base64 encoded certificate
    private String logo;                     // Base64 encoded logo
}
