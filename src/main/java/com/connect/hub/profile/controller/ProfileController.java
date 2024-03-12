package com.connect.hub.profile.controller;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.service.BlogService;
import com.connect.hub.profile.dto.ProfileDTO;
import com.connect.hub.profile.model.Profile;
import com.connect.hub.profile.repository.ProfileRepository;
import com.connect.hub.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private BlogService blogService;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    final String emailId = "ashwinnanbazhagan@gmail.com";


    @GetMapping()
    public Profile getProfile(){
        return profileService.getProfile(emailId);
    }


    @PutMapping()
    public Profile editProfile(@RequestBody ProfileDTO  profileDto){
        return profileService.editProfile(profileDto,emailId);
    }

    @PostMapping(value = "/uploadImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(@RequestPart(required = false) MultipartFile file) throws IOException {
        return profileService.uploadProfilePicture(file,emailId);
    }

    @GetMapping("/blogs")

    public ResponseEntity<?> getBlogs(){
       List<Blog> blogList = blogService.retrieveBlogs(emailId);
       return new ResponseEntity<>(blogList,HttpStatus.OK);
    }

}
