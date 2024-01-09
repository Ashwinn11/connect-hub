package com.connect.authentication.profile.service;

import com.connect.authentication.model.User;
import com.connect.authentication.profile.model.Profile;
import com.connect.authentication.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    public void mapUserToProfile(User user){
        Profile profile = Profile.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .state(user.getState())
                .regionCode(user.getRegionCode())
                .emailId(user.getEmailId())
                .country(user.getCountry())
                .regionCode(user.getRegionCode())
                .build();
        profileRepository.save(profile);

    }
}
