package com.connect.hub.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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
    @ManyToMany(mappedBy = "blogs",fetch = FetchType.LAZY)
    private List<Tag> tag;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    @JsonIgnore
    private byte[] file;
    private String fileName;
    private String filetype;
    private long likes;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
