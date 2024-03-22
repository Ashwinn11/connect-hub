package com.connect.hub.community.controller;

import com.connect.hub.auth.model.User;
import com.connect.hub.auth.service.UserService;
import com.connect.hub.community.model.CommunityDTO;
import com.connect.hub.community.service.CommunityService;
import com.connect.hub.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/api/communities")
public class CommunityController{
    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;
    @PostMapping("/create-community")
    public ResponseEntity<?> createCommunity(@RequestBody CommunityDTO communityDTO){
        communityService.createCommunity(communityDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/join-community")
    public ResponseEntity<?> joinCommunity(@RequestParam Long id) throws CustomException {
        String emailId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmailId(emailId);
        return communityService.joinCommunity(user,id);
    }
}
