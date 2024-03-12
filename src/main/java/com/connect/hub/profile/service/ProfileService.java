package com.connect.hub.profile.service;

import com.connect.hub.auth.model.User;
import com.connect.hub.profile.dto.ProfileDTO;
import com.connect.hub.profile.model.Profile;
import com.connect.hub.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(value = "profile",key = "#emailId")
    public Profile editProfile(ProfileDTO profileDto,String emailId) {
        Profile profile = getProfile(emailId);
        profile.setBio(profileDto.getBio());
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profileRepository.save(profile);
        return profile;
    }

    @CachePut(value="profile",key = "#emailId")
    public ResponseEntity<?> uploadProfilePicture(MultipartFile file, String emailId) throws IOException {
        Profile profile = getProfile(emailId);
        profile.setImageData(file.getBytes());
        profile.setImageName(file.getOriginalFilename());
        profile.setImageType(file.getContentType());
        profileRepository.save(profile);
        return new ResponseEntity<>("Profile picture updated successfully!", HttpStatusCode.valueOf(200));
    }
    @Cacheable(value = "profile",key = "#emailId")
    public Profile getProfile(String emailId) {
        return profileRepository.findByEmailId(emailId);
    }
}
