package com.connect.hub.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name="blog_id")
    @JsonIgnore
    private Blog blog;
}
