package com.dennis.Email_Service.service;

import com.dennis.Email_Service.model.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendOtpEmail(String email, String otp) {
        // Logic to send OTP email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("confirmation otp");
        message.setText(otp);
        mailSender.send(message);
    }

    public void sendApprovalEmail(String email) {
        // Logic to send approval email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Account Approval");
        message.setText("Your account is now verified." +
                "Enjoy your time on the platform.\n\nSincerely,\nskillspace.admin@email.com"
        );
        mailSender.send(message);
    }

    public void sendRejectionEmail(String email) {
        // Logic to send rejection email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Account Rejected");
        message.setText("After going through your registration details, " +
                "your account registration for skillspace has been rejected." +
                "\n\nSincerely,\nskillspace.admin@email.com"
        );
        mailSender.send(message);
    }

    public void sendGenericMail(Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        mailSender.send(message);
    }
}

