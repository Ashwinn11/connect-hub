package com.connect.authentication.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProfileDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String emailId;
    private String regionCode;
    private String state;
    private String country;
    private String bio;
}
