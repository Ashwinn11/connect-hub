package com.connect.hub.mail.repository;

import com.connect.hub.mail.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {
    OTP findByEmailId(String emailId);
}
