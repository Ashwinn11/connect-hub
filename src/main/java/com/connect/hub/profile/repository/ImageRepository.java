package com.connect.hub.profile.repository;

import com.connect.hub.profile.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageData,Long> {
}
