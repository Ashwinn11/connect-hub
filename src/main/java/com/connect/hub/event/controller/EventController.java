package com.connect.hub.event.controller;

import com.connect.hub.event.model.Event;
import com.connect.hub.event.model.EventEnroll;
import com.connect.hub.event.repository.EventRepository;
import com.connect.hub.event.service.EnrollService;
import com.connect.hub.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private EventService eventService;
    @GetMapping("/event-list")
    public List<Event> getEventList(){
        return eventRepository.findAll();
    }

    @PostMapping("/create-event")
    public ResponseEntity<String> createUpcomingEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @PostMapping("/enroll")
    public ResponseEntity<HttpStatusCode> enroll(@RequestBody EventEnroll enroll) throws InterruptedException {
        return enrollService.enroll(enroll);
    }

}
