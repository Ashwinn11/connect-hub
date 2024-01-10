package com.connect.hub.auth.controller;

import com.connect.hub.auth.model.JwtResponse;
import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SignupController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> signup(@RequestBody Signup signup) throws Exception {
       return ResponseEntity.ok(userService.registerUser(signup));
    }

}
