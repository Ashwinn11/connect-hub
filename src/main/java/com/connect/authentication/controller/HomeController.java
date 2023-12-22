package com.connect.authentication.controller;

import com.connect.authentication.dto.LoginDTO;
import com.connect.authentication.model.User;
import com.connect.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getEnd(){
        return "index";
    }
}
