package com.connect.hub.auth.service;

import com.connect.hub.auth.model.Role;
import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.model.User;
import com.connect.hub.exception.CustomException;
import com.connect.hub.mail.controller.EmailController;
import com.connect.hub.mail.service.EmailService;
import com.connect.hub.profile.service.ProfileService;
import com.connect.hub.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> sendOTP(Signup signup) throws CustomException {

        Optional<User> user = userRepository.findByEmailId(signup.getEmailId());
        if (user.isPresent()) {
            return new ResponseEntity<>("Email-ID already exists. Please try logging in!",HttpStatus.IM_USED);
        }
        return emailService.sendSignupEmail(signup.getEmailId());

    }

    public User getUserByEmailId(String emailId) throws CustomException {
        Optional<User> user = userRepository.findByEmailId(emailId);
        if (user.isEmpty()){
            throw new CustomException("User not found");
        }
        return user.get();
    }

    public User buildUser(Signup signup){
        User user = User.builder()
                .firstName(signup.getFirstName())
                .lastName(signup.getLastName())
                .emailId(signup.getEmailId())
                .mobileNo(signup.getMobileNo())
                .password(passwordEncoder.encode(signup.getPassword()))
                .role(Role.USER).build();
        return user;

    }


}
