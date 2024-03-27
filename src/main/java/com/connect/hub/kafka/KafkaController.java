package com.connect.hub.kafka;

import com.connect.hub.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message){
        kafkaProducer.sendMessage("my-topic",message);
        return "Message sent-> "+message;
    }
}
