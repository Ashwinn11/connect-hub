package com.connect.hub.auth.service;

import com.connect.hub.auth.model.JwtResponse;
import com.connect.hub.auth.model.Role;
import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.model.User;
import com.connect.hub.profile.service.ProfileService;
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

    @Autowired
    private ProfileService profileService;
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
        profileService.mapUserToProfile(user);
        String token = jwtService.generateToken(user);
        return JwtResponse.builder().token(token).build();

    }

}
