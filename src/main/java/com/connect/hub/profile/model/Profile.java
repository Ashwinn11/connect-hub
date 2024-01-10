package com.connect.hub.profile.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String imageUrl;

    @Column(nullable = false,unique = true)
    private String emailId;

    @Column(nullable = false)
    private String regionCode;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    private String bio;

}
