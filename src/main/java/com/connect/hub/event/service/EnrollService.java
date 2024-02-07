package com.connect.hub.event.service;

import com.connect.hub.event.model.Event;
import com.connect.hub.event.model.EventEnroll;
import com.connect.hub.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class EnrollService {
    @Autowired
    private EventRepository eventRepository;
    private final Lock lock = new ReentrantLock();
    public ResponseEntity<HttpStatusCode> enroll(EventEnroll enroll) throws InterruptedException {
        Optional<Event> optionalEvent = eventRepository.findById(enroll.getEventId());
        if (optionalEvent.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            Event event = optionalEvent.get();
            if (event.getLimit()<=0){
                return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
            }
            else {
                booking(event);
            }
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public void booking(Event event){
        Thread bookingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    event.setLimit(event.getLimit() - 1);
                    eventRepository.save(event);
                }
                finally {
                    lock.unlock();
                }

            }
        });
        bookingThread.start();
        try {
            bookingThread.join();
        }
        catch (InterruptedException exception){
            bookingThread.interrupt();
        }

    }
}
