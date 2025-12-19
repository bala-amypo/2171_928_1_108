package com.example.demo.service;

import com.example.demo.model.EventRecord;
import java.util.List;

public interface EventRecordService {

    EventRecord createEvent(EventRecord event);

    EventRecord getEventById(Long id);

    EventRecord getEventByCode(String eventCode);

    List<EventRecord> getAllEvents();

    void updateEventStatus(Long id, boolean active);
}
