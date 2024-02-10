package com.connect.hub.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Signup {

    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String mobileNo;
    private String regionCode;
    private String state;
    private String country;
}
