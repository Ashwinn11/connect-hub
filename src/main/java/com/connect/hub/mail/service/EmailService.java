package com.connect.hub.mail.service;

import com.connect.hub.auth.model.Role;
import com.connect.hub.auth.model.Signup;
import com.connect.hub.auth.model.User;
import com.connect.hub.auth.repository.UserRepository;
import com.connect.hub.auth.service.UserService;
import com.connect.hub.mail.model.OTP;
import com.connect.hub.mail.repository.OTPRepository;
import com.connect.hub.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;



    public ResponseEntity<?> sendSignupEmail(String emailId){
        Random random = new Random();
        int otp = random.nextInt(999999);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("ConnectHub - OTP ");
        String text = String.format("Dear user,Please enter the verification code (%06d) to complete the Sign-up process.",otp);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
        otpObject(emailId,otp);
        return new ResponseEntity<>("Email sent successfully! Please entry the otp for verification.",HttpStatus.OK);
    }

    public ResponseEntity<?> verifyOtp(int otp, String emailId, Signup signup){
        OTP object = otpRepository.findByEmailId(emailId);
        if (object.getOtp() == otp && LocalDateTime.now().isBefore(object.getCreationTime().plusSeconds(60))){
            User user = userService.buildUser(signup);
            userRepository.save(user);
            profileService.mapUserToProfile(user);
            return new ResponseEntity<>("Sign-up successful.", HttpStatus.ACCEPTED);

        }
        return new ResponseEntity<>("OTP is not valid. Please try again later!",HttpStatus.NOT_ACCEPTABLE);
    }

    public void otpObject(String emailId, int otp){
        OTP otpObject = new OTP();
        otpObject.setEmailId(emailId);
        otpObject.setCreationTime(LocalDateTime.now());
        otpObject.setOtp(otp);
        otpRepository.save(otpObject);
    }



    public void sendCommentNotification(String emailId){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Wake-up! ");
        String text = "Someone commented on your post";
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    public ResponseEntity<?> recoverOtp(String emailId) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
