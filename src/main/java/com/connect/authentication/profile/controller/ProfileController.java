package com.connect.authentication.profile.controller;

import com.connect.authentication.profile.dto.ProfileDTO;
import com.connect.authentication.profile.model.Profile;
import com.connect.authentication.profile.repository.ProfileRepository;
import com.connect.authentication.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
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
}
