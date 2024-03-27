package com.connect.hub.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class BlogDTO {
    public String title;
    public String body;
    public String tag;
    public MultipartFile file;

}
