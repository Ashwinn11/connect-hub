package com.connect.hub.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CommunityDTO {
    private String name;
    private String emailId;
    private String description;
}
