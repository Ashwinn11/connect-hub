package com.connect.hub.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String emailId;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String body;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Tag> tag;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] file;
    private long likes;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
