package com.example.demo.service;

import com.example.demo.model.EventRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventRecordServiceImpl implements EventRecordService {

    private final List<EventRecord> events = new ArrayList<>();

    @Override
    public EventRecord createEvent(EventRecord event) {
        events.add(event);
        return event;
    }

    @Override
    public EventRecord getEventById(Long id) {
        return events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public EventRecord getEventByCode(String eventCode) {
        return events.stream()
                .filter(e -> e.getEventCode().equals(eventCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<EventRecord> getAllEvents() {
        return events;
    }

    @Override
    public void updateEventStatus(Long id, boolean active) {
        for (EventRecord event : events) {
            if (event.getId().equals(id)) {
                event.setActive(active);
                break;
            }
        }
    }
}
