package com.connect.hub.mail.controller;

import com.connect.hub.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> sendOtp(@RequestParam String emailId){
        return emailService.sendSignupEmail(emailId);
    }

    @PostMapping("/signup/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam int otp , @RequestParam String emailId){
        return emailService.veriyOtp(otp,emailId);
    }

}
