package com.connect.hub.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class BlogDTO {
    public String title;
    public String body;
    public String tag;

}
