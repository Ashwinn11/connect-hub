package com.connect.hub.blog.service;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.BlogDTO;
import com.connect.hub.blog.model.Tag;
import com.connect.hub.blog.repository.BlogRepository;
import com.connect.hub.blog.repository.TagRepository;
import com.connect.hub.exception.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;
    @Transactional(rollbackOn = Exception.class)
    public void createBlog(BlogDTO blog, String emailId) throws IOException, InterruptedException {
        MultipartFile file = blog.file;
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
        Tag tag = null;
        try {
            tag = createOrUpdateTag(blog.tag, blogData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tag != null) {
            blogData.setTag(List.of(tag));
        }
        blogRepository.save(blogData);
    }
    public synchronized Tag createOrUpdateTag(String tagName ,Blog blogData) throws InterruptedException {
        Optional<Tag> optional = tagRepository.findById(tagName);
        if (optional.isPresent()) {
            Tag tag = optional.get();
            List<Blog> blogList = tag.getBlogs();
            blogList.add(blogData);
            tag.setBlogs(blogList);
            return tagRepository.save(tag);
        } else {
            Tag tag = new Tag();
            tag.setName(tagName);
            tag.setBlogs(List.of(blogData));
            try {
                return tagRepository.save(tag);
            } catch (Exception e) {
                // Handle duplicate entry exception
                Thread.sleep(2000); // Wait before retrying
                return createOrUpdateTag(tagName, blogData); // Retry tag creation
            }
        }
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

    public ResponseEntity<?> editBlog(BlogDTO blogDTO, Long id, String emailId) throws IOException {
        Optional<Blog> optional = blogRepository.findById(id);
        if(Objects.equals(emailId,optional.get().getEmailId())){
            Blog blog = optional.get();
            blog.setTitle(blogDTO.title);
            blog.setBody(blogDTO.body);
            blog.setFile(blogDTO.file.getBytes());
            blog.setFiletype(blogDTO.file.getContentType());
            blog.setFileName(blogDTO.file.getOriginalFilename());
            blogRepository.save(blog);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    public ResponseEntity<?> deleteBlog(Long id, String emailId) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (Objects.equals(blog.get().getEmailId(), emailId)) {
            blogRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    public List<Blog> retrieveBlogs(String emailId){
        List<Blog> blogList = blogRepository.findByEmailId(emailId);
        return blogList;
    }

    public Blog getBlogByID(Long id) {
        return blogRepository.findById(id).get();
    }
}
