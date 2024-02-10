package com.connect.hub.mail.service;

import com.connect.hub.mail.model.OTP;
import com.connect.hub.mail.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OTPRepository otpRepository;


    public ResponseEntity<?> sendSignupEmail(String emailId){
        Random random = new Random();
        int otp = random.nextInt(999999);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("ConnectHub - OTP ");
        String text = String.format("Dear user,Please enter the verification code (%06d) to complete the Sign-up process.",otp);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
        OTP otp1 = new OTP();
        otp1.setEmailId(emailId);
        otp1.setCreationTime(LocalDateTime.now());
        otp1.setOtp(otp);
        otpRepository.save(otp1);
        return new ResponseEntity<>("Email sent successfully! Please entry the otp for verification.",HttpStatus.CONTINUE);
    }

    public ResponseEntity<?> veriyOtp(int otp,String emailId){
        OTP object = otpRepository.findByEmailId(emailId);
        if (object.getOtp() == otp && LocalDateTime.now().isBefore(object.getCreationTime().plusSeconds(60))){
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_ACCEPTABLE);
    }

}
