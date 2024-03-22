package com.connect.hub.blog.controller;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.Comment;
import com.connect.hub.blog.service.BlogService;
import com.connect.hub.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/blogs/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;


    @PostMapping("/{id}")
    public ResponseEntity<?> addComment(@PathVariable Long id, @RequestBody Comment comment){
        Blog blog = blogService.getBlogByID(id);
        Comment getComment = commentService.createComment(blog,comment);
        return new ResponseEntity<>(getComment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public List<Comment> getComments(@PathVariable Long id ){
        Blog blog = blogService.getBlogByID(id);
        List<Comment> commentList = commentService.listComments(blog);
        return commentList;
    }
}
