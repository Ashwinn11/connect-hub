package com.connect.hub.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseEntity<HttpStatusCode> sendSignupEmail(String emailId){
        Random random = new Random();
        int otp = random.nextInt(999999);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("ConnectHub- OTP ");

        String text = String.format("Dear user,Please enter the verification code (%06d) to complete the Sign-up process.",otp);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
