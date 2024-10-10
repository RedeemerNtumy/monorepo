package com.dennis.Email_Service.service;


import com.dennis.Email_Service.config.RabbitConfig;
import com.dennis.Email_Service.model.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
    public void receiveEmail(Email email) {
        emailService.sendOtpEmail(email.getTo(), email.getBody()); // Assuming body contains the OTP
    }
}
