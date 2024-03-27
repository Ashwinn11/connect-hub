package com.connect.hub.blog.controller;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.BlogDTO;
import com.connect.hub.blog.service.BlogService;
import com.connect.hub.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/blogs")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(path = "/publish", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> publishBlog(@RequestPart(required = false) MultipartFile file,@RequestPart("title") String title,@RequestPart("body") String body, @RequestPart("tag") String tag ) throws IOException, InterruptedException {
        BlogDTO blog = new BlogDTO();
        blog.setTitle(title);
        blog.setTag(tag);
        blog.setFile(file);
        blog.setBody(body);
        blogService.createBlog(blog,getEmailId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/view")
    public List<Blog> viewBlogs(){
        return blogService.getBlogs();
    }

    @GetMapping("/tag")
    public List<Blog> getBlogsFromTag(@RequestParam String tag) throws CustomException {
        return blogService.getBlogs(tag);
    }

    @PutMapping(value = "/edit",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> editBlog(@RequestPart(required = false) MultipartFile file , @RequestPart String title, @RequestPart String body , @RequestParam Long id) throws IOException {
        BlogDTO blog = new BlogDTO();
        blog.setTitle(title);
        blog.setFile(file);
        blog.setBody(body);
        return blogService.editBlog(blog,id,getEmailId());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBlog(@RequestParam Long id){
        return blogService.deleteBlog(id,getEmailId());
    }

    public String getEmailId(){
        String emailId = SecurityContextHolder.getContext().getAuthentication().getName();
        return emailId="ashwinnanbazhagan@gmail.com";
    }

}
