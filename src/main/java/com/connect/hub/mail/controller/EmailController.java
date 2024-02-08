package com.connect.hub.mail.controller;

import com.connect.hub.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

}
