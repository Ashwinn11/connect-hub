package com.connect.hub.blog.service;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.BlogDTO;
import com.connect.hub.blog.model.Tag;
import com.connect.hub.blog.repository.BlogRepository;
import com.connect.hub.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;

    public void createBlog(BlogDTO blog, String emailId, MultipartFile file) throws IOException {

        Blog blogData = Blog.builder()
                .body(blog.body)
                .emailId(emailId)
                .file(file.getBytes())
                .likes(0)
                .title(blog.title)
                .build();
        Tag tag = new Tag();
        tag.setName(blog.tag);
        tag.setBlog(blogData);
        blogData.setTag(List.of(tag));
        blogRepository.save(blogData);
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }
    public List<Blog> getBlogs(String tag) {
        List<Tag> tagList = tagRepository.findByName(tag);
        List<Blog> blogList = new ArrayList<>();
        for (Tag tags : tagList){
            blogList.add(tags.getBlog());
        }
        return blogList;
    }
}
