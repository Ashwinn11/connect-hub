package com.connect.hub.blog.service;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.BlogDTO;
import com.connect.hub.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public void createBlog(BlogDTO blog, String emailId) {
        Blog blogData = Blog.builder()
                .body(blog.body)
                .tag(blog.tag)
                .emailId(emailId)
                .likes(0)
                .title(blog.title)
                .build();
        blogRepository.save(blogData);
    }
}
