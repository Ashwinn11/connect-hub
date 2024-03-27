package com.connect.hub.kafka.consumer;


import com.connect.hub.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "comment-notification",groupId = "my-group-id")
    public void listen(String message){
        emailService.sendCommentNotification(message);
        System.out.println("Sent->"+message);
    }
}
