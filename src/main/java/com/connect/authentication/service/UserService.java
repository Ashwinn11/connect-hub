package com.connect.authentication.service;

import com.connect.authentication.model.JwtResponse;
import com.connect.authentication.model.Role;
import com.connect.authentication.model.Signup;
import com.connect.authentication.model.User;
import com.connect.authentication.profile.model.Profile;
import com.connect.authentication.profile.service.ProfileService;
import com.connect.authentication.repository.UserRepository;
import com.connect.authentication.validator.SignupValidator;
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
