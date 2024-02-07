package com.connect.hub.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("ashwinnanbazhagan@gmail.com");
        mailMessage.setSubject("checking the service");
        mailMessage.setText("First ever mail from the application");
        javaMailSender.send(mailMessage);
    }
}
