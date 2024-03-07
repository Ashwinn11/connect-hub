package com.connect.hub.blog.controller;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.BlogDTO;
import com.connect.hub.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    final String emailId = authentication.getName();

    @PostMapping("/publish")
    public ResponseEntity<?> publishBlog(@RequestBody BlogDTO blog){
        blogService.createBlog(blog,emailId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
