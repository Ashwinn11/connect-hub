package com.connect.hub.community.service;

import com.connect.hub.auth.model.User;
import com.connect.hub.auth.repository.UserRepository;
import com.connect.hub.community.model.Community;
import com.connect.hub.community.model.CommunityDTO;
import com.connect.hub.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public void createCommunity(CommunityDTO communityDTO,User user , String emailId) {
        Community community = new Community();
        community.setCreatedBy(emailId);
        community.setName(communityDTO.getName());
        community.setCreationDate(LocalDate.now());
        community.setDescription(communityDTO.getDescription());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        community.setUsers(userList);
        communityRepository.save(community);
    }

    public ResponseEntity<?> joinCommunity(User user,Long id) {
        Optional<Community> optionalCommunity = communityRepository.findById(id);
        if(optionalCommunity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Community community = optionalCommunity.get();
        List<User> userList = community.getUsers();
        if (userList.contains(user)){
            return new ResponseEntity<>("You are already enrolled to the community.!",HttpStatus.FOUND);
        }
        userList.add(user);
        community.setUsers(userList);
        communityRepository.save(community);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}
