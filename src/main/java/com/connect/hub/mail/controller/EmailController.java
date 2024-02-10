package com.connect.hub.mail.controller;

import com.connect.hub.auth.model.Signup;
import com.connect.hub.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/signup/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam int otp , @RequestParam String emailId , @RequestBody Signup signup){
        return emailService.verifyOtp(otp,emailId,signup);
    }

}
