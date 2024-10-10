package com.dennis.user_service.client;

import com.dennis.user_service.dto.EmailApproveRejectDto;
import com.dennis.user_service.dto.EmailConfirmOtpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class
EmailClient {

    private final String URL = "http://EMAIL-SERVICE/api/email";
    @Autowired
    RestTemplate restTemplate;

    public void sendOtp(String email, String otp){
        EmailConfirmOtpDto emailConfirmOtpDto = new EmailConfirmOtpDto(email, otp);
        ResponseEntity<String> response =restTemplate.postForEntity(URL + "/send-otp", emailConfirmOtpDto, String.class);
        response.getBody();
    }


    public void sendApprovalEmail(String email) {
        EmailApproveRejectDto emailApproveRejectDto = new EmailApproveRejectDto(email);
        ResponseEntity<String> response =restTemplate.postForEntity(URL + "/approve", emailApproveRejectDto, String.class);
        response.getBody();
    }

    public void sendRejectionEmail(String email) {
        EmailApproveRejectDto emailApproveRejectDto = new EmailApproveRejectDto(email);
        ResponseEntity<String> response =restTemplate.postForEntity(URL + "/reject", emailApproveRejectDto, String.class);
        response.getBody();
    }
}
