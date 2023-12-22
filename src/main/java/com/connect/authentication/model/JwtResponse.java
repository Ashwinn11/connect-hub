package com.connect.authentication.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
