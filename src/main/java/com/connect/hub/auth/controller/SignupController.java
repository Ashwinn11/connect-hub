package com.connect.hub.auth.controller;

import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.service.UserService;
import com.connect.hub.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Signup signup) throws Exception {
       return userService.registerUser(signup);
    }



}
