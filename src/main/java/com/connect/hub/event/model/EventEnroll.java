package com.connect.hub.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EventEnroll {
    public String emailId;
    public long eventId;
}
