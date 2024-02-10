package com.connect.hub.auth.service;

import com.connect.hub.auth.model.Role;
import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.model.User;
import com.connect.hub.mail.controller.EmailController;
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
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JwtService jwtService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private EmailController emailController;
    public ResponseEntity<?> registerUser(Signup signup) {

        Optional<User> userCheck = userRepository.findByEmailId(signup.getEmailId());
        if (userCheck.isPresent()) {
            return new ResponseEntity<>("Email-ID already exists. Please try logging in!",HttpStatus.IM_USED);
        }
        emailController.sendOtp(signup.getEmailId());
        User user = buildUser(signup);
        userRepository.save(user);
        profileService.mapUserToProfile(user);
        return new ResponseEntity<>("Sign-up successful.", HttpStatus.ACCEPTED);

    }

    public User buildUser(Signup signup){
        User user = User.builder()
                .firstName(signup.getFirstName())
                .lastName(signup.getLastName())
                .emailId(signup.getEmailId())
                .mobileNo(signup.getMobileNo())
                .country(signup.getCountry())
                .regionCode(signup.getRegionCode())
                .state(signup.getState())
                .password(passwordEncoder.encode(signup.getPassword()))
                .role(Role.USER).build();
        return user;

    }
}
