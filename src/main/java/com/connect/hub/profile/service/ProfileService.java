package com.connect.hub.profile.service;

import com.connect.hub.auth.model.User;
import com.connect.hub.profile.dto.ProfileDTO;
import com.connect.hub.profile.model.Profile;
import com.connect.hub.profile.repository.ProfileRepository;
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

    public Profile editProfile(ProfileDTO profileDto,Profile profile) {
        profile.setBio(profileDto.getBio());
        profile.setCountry(profileDto.getCountry());
        profile.setState(profileDto.getState());
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setImageUrl(profileDto.getImageUrl());
        profile.setRegionCode(profileDto.getRegionCode());
        profileRepository.save(profile);
        return profile;
    }
}
