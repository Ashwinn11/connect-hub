package com.connect.hub.profile.controller;

import com.connect.hub.profile.dto.ProfileDTO;
import com.connect.hub.profile.model.ImageData;
import com.connect.hub.profile.model.Profile;
import com.connect.hub.profile.repository.ProfileRepository;
import com.connect.hub.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    public Profile getEmailFromAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailId = authentication.getName();
        return profileRepository.findByEmailId(emailId);
    }
    @GetMapping()
    public Profile getProfile(){
        return getEmailFromAuthentication();
    }
    @PutMapping()
    public Profile editProfile(@RequestBody ProfileDTO  profileDto){

        return profileService.editProfile(profileDto,getEmailFromAuthentication());
    }
    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file) throws IOException {
        ImageData data = ImageData.builder().name(file.getOriginalFilename()).
                type(file.getContentType()).build();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
