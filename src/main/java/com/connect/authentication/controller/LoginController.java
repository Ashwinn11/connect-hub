package com.connect.authentication.controller;
import com.connect.authentication.dto.LoginDTO;
import com.connect.authentication.model.JwtResponse;
import com.connect.authentication.model.User;
import com.connect.authentication.repository.UserRepository;
import com.connect.authentication.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public AuthenticationManager auth;
    @Autowired
    private JwtService jwtService;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginAuth(@RequestBody LoginDTO loginDTO) {
        Optional<User> user1 = userRepository.findByEmailId(loginDTO.getEmailId());
        try {
            auth.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmailId(), loginDTO.getPassword()));
        }
        catch (BadCredentialsException e) {
            log.error("Bad credentials for user: {}", loginDTO.getEmailId(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (Exception ex) {
            log.error("Exception during authentication for user: {}", loginDTO.getEmailId(), ex);
        }
        User user = userRepository.findByEmailId(loginDTO.getEmailId()).orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(JwtResponse.builder().token(token).build());
    }
}
