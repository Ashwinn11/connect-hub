package com.connect.hub.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class Signup {
    public String firstName;
    public String lastName;
    public String emailId;
    public String password;
    public String mobileNo;

}

