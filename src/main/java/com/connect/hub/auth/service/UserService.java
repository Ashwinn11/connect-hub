package com.connect.hub.auth.service;

import com.connect.hub.auth.model.Role;
import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.model.User;
import com.connect.hub.mail.controller.EmailController;
import com.connect.hub.mail.service.EmailService;
import com.connect.hub.profile.service.ProfileService;
import com.connect.hub.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    public ResponseEntity<?> sendOTP(Signup signup) {

        Optional<User> userCheck = userRepository.findByEmailId(signup.getEmailId());
        if (userCheck.isPresent()) {
            return new ResponseEntity<>("Email-ID already exists. Please try logging in!",HttpStatus.IM_USED);
        }
        return emailService.sendSignupEmail(signup.getEmailId());

    }


}
