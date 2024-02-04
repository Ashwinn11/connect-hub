package com.connect.hub.auth.service;

import com.connect.hub.auth.model.JwtResponse;
import com.connect.hub.auth.model.Role;
import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.model.User;
import com.connect.hub.auth.repository.UserRepository;
import com.connect.hub.auth.validator.SignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JwtService jwtService;

    public SignupValidator validator;

    public JwtResponse registerUser(Signup signup) throws Exception {
        User user = User.builder()
                .firstName(signup.getFirstName())
                .lastName(signup.getLastName())
                .emailId(signup.getEmailId())
                .mobileNo(signup.getMobileNo())
                .password(passwordEncoder.encode(signup.getPassword()))
                .country(signup.getCountry())
                .regionCode(signup.getRegionCode())
                .state(signup.getState())
                .role(Role.USER).build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return JwtResponse.builder().token(token).build();

    }

}
