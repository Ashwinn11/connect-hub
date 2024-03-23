package com.connect.hub.blog.service;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.BlogDTO;
import com.connect.hub.blog.model.Tag;
import com.connect.hub.blog.repository.BlogRepository;
import com.connect.hub.blog.repository.TagRepository;
import com.connect.hub.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;

    public void createBlog(BlogDTO blog, String emailId, MultipartFile file) throws IOException, InterruptedException {

        Blog blogData = Blog.builder()
                .body(blog.body)
                .emailId(emailId)
                .file(file.getBytes())
                .fileName(file.getOriginalFilename())
                .filetype(file.getContentType())
                .likes(0)
                .createdAt(LocalDateTime.now())
                .title(blog.title)
                .build();
        Optional<Tag> optional = tagRepository.findById(blog.tag);
        if(optional.isPresent()){
            List<Blog> blogList = optional.get().getBlogs();
            blogList.add(blogData);
            optional.get().setBlogs(blogList);
            blogData.setTag(List.of(optional.get()));
        }else{
            Tag tag = new Tag();
            tag.setName(blog.tag);
            tag.setBlogs(List.of(blogData));
            try {
                tagRepository.save(tag);
            }
            catch (Exception duplicateEntry){
                Thread.sleep(2000);
                tagRepository.save(tag);
            }
            blogData.setTag(List.of(tag));

        }

        blogRepository.save(blogData);
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }
    public List<Blog> getBlogs(String tagName) throws CustomException {
        Optional<Tag> tag = tagRepository.findById(tagName);
        if(tag.isEmpty()){
            throw new CustomException("There is no such tag present!");
        }
        List<Blog> blogList = blogRepository.findByTag(tag.get());
        return blogList;
    }
    @CacheEvict(value = "profile", key = "#emailId")
    @CachePut(value = "profile", key = "#emailId")
    public ResponseEntity<?> editBlog(MultipartFile file, String title, String body, Long id, String emailId) throws IOException {
        List<Blog> blogList = retrieveBlogs(emailId);
        for (Blog blog : blogList) {
            if (blog.getId()==id) {
                blog.setBody(body);
                blog.setFile(file.getBytes());
                blog.setFiletype(file.getContentType());
                blog.setFileName(file.getOriginalFilename());
                blog.setTitle(title);
                blogRepository.save(blog);
            }
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @CacheEvict(value = "blogs",key = "#emailId")
    public ResponseEntity<?> deleteBlog(Long id, String emailId) {
       List<Blog> blogList = retrieveBlogs(emailId);
       for (Blog blogs : blogList){
           if (blogs.getId()==id){
               blogRepository.deleteById(blogs.getId());
           }
       }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Cacheable(value = "blogs",key = "#emailId")
    public List<Blog> retrieveBlogs(String emailId){
        List<Blog> blogList = blogRepository.findByEmailId(emailId);
        return blogList;
    }

    public Blog getBlogByID(Long id) {
        return blogRepository.findById(id).get();
    }
}
