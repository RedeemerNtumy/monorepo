package com.dennis.Email_Service.controller;

import com.dennis.Email_Service.dto.EmailApproveRejectDto;
import com.dennis.Email_Service.dto.EmailConfirmOtpDto;
import com.dennis.Email_Service.model.Email;
import com.dennis.Email_Service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> SendOtp(@RequestBody EmailConfirmOtpDto emailConfirmOtpDto) {
        emailService.sendOtpEmail(emailConfirmOtpDto.getEmail(), emailConfirmOtpDto.getOtp());
        return new ResponseEntity<>("OTP sent", HttpStatus.OK);
    }

    @PostMapping("/approve")
    public ResponseEntity<String> sendApproval(@RequestBody EmailApproveRejectDto emailApproveRejectDto) {
        emailService.sendApprovalEmail(emailApproveRejectDto.getEmail());
        return new ResponseEntity<>("approval mail sent", HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity<String> sendReject(@RequestBody EmailApproveRejectDto emailApproveRejectDto) {
        emailService.sendRejectionEmail(emailApproveRejectDto.getEmail());
        return new ResponseEntity<>("rejection mail sent", HttpStatus.OK);
    }

    @PostMapping("/generic")
    public ResponseEntity<String> sendGeneric(@RequestBody Email email) {
        emailService.sendGenericMail(email);
        return new ResponseEntity<>("mail sent", HttpStatus.OK);
    }

}
