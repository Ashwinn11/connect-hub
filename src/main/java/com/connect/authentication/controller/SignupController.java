package com.connect.authentication.controller;

import com.connect.authentication.model.JwtResponse;
import com.connect.authentication.model.Signup;
import com.connect.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> signup(@RequestBody Signup signup) throws Exception {
       return ResponseEntity.ok(userService.registerUser(signup));
    }

}
