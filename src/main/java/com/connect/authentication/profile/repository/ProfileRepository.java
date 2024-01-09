package com.connect.authentication.profile.repository;

import com.connect.authentication.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByEmailId(String emailId);
}
