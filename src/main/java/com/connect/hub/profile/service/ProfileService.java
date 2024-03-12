package com.connect.hub.profile.service;

import com.connect.hub.auth.model.User;
import com.connect.hub.profile.dto.ProfileDTO;
import com.connect.hub.profile.model.Profile;
import com.connect.hub.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;


    public void mapUserToProfile(User user){
        Profile profile = Profile.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailId(user.getEmailId())
                .build();
        profileRepository.save(profile);
    }

    public Profile editProfile(ProfileDTO profileDto,Profile profile) {
        profile.setBio(profileDto.getBio());
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profileRepository.save(profile);
        return profile;
    }

    public ResponseEntity<?> uploadProfilePicture(MultipartFile file, String emailId) throws IOException {
        Profile profile = profileRepository.findByEmailId(emailId);
        profile.setImageData(file.getBytes());
        profile.setImageName(file.getOriginalFilename());
        profile.setImageType(file.getContentType());
        profileRepository.save(profile);
        return new ResponseEntity<>("Profile picture updated successfully!", HttpStatusCode.valueOf(200));
    }
}
