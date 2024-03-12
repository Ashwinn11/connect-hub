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

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    private String imageName;

    private String imageType;

    @Column(nullable = false,unique = true)
    private String emailId;

    private String bio;

}
