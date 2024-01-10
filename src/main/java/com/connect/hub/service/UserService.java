package com.connect.hub.service;

import com.connect.hub.model.JwtResponse;
import com.connect.hub.model.Role;
import com.connect.hub.model.Signup;
import com.connect.hub.model.User;
import com.connect.hub.repository.UserRepository;
import com.connect.hub.validator.SignupValidator;
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
                .country(signup.getCountry())
                .regionCode(signup.getRegionCode())
                .state(signup.getState())
                .password(passwordEncoder.encode(signup.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return JwtResponse.builder().token(token).build();

    }

}
