package com.connect.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginDTO {
    public String emailId;
    public String password;
}
