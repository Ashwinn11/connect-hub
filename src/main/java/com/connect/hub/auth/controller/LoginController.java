package com.connect.hub.auth.controller;

import com.connect.hub.auth.dto.LoginDTO;
import com.connect.hub.auth.model.JwtResponse;
import com.connect.hub.auth.model.User;
import com.connect.hub.auth.repository.UserRepository;
import com.connect.hub.auth.service.JwtService;
import com.connect.hub.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public AuthenticationManager auth;

    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> loginAuth( @RequestBody LoginDTO loginDTO) {
        try {
            auth.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmailId(), loginDTO.getPassword()));
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Entered Email-ID or Password is incorrect.");
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is an error occurred. Please try after sometime.");
        }
        User user = userRepository.findByEmailId(loginDTO.getEmailId()).orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(JwtResponse.builder().token(token).build());
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<?> changePassword(@RequestParam String emailId){
        Optional<User> user = userRepository.findByEmailId(emailId);
        if (user.isEmpty()){
            return new ResponseEntity<>("Email-Id doesn't exist.!",HttpStatus.NOT_FOUND);
        }
        return emailService.recoverOtp(emailId);
    }

}
