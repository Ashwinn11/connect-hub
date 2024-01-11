package com.connect.hub.event.controller;

import com.connect.hub.event.Event;
import com.connect.hub.event.repository.EventRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @GetMapping(value = "/event")
    public List<Event> getName(){
        return eventRepository.findAll();
    }

    @GetMapping("/sample")
    public String output(){
        return "Ashwin";
    }
    @PostMapping("/event")
    public ResponseEntity<?> postEvent(@RequestBody Event event){
        eventRepository.save(event);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
