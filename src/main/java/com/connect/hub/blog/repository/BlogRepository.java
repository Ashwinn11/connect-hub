package com.connect.hub.blog.repository;

import com.connect.hub.blog.model.Blog;
import com.connect.hub.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByEmailId(String emailId);

    List<Blog> findByTag(Tag tagName);
}
