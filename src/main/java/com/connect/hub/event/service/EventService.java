package com.connect.hub.event.service;

import com.connect.hub.event.model.Event;
import com.connect.hub.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    public ResponseEntity<String> createEvent(Event event) {
        LocalDateTime localDateTime = event.getLocalDateTime();
        if(localDateTime.isAfter(LocalDateTime.now())) {
            eventRepository.save(event);
            return new ResponseEntity<>("New event has been created", HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
}
