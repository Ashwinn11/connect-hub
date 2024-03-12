package com.connect.hub.blog.service;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.Comment;
import com.connect.hub.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    public Comment createComment(Blog blog, Comment comment) {
        Comment savedComment = new Comment();
        savedComment.setComment(comment.getComment());
        savedComment.setBlog(blog);
        commentRepository.save(savedComment);
        return savedComment;
    }

    public List<Comment> listComments(Blog blog) {
        return blog.getComments();
    }
}
