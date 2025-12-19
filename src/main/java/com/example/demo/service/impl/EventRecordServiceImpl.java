package com.example.demo.service.impl;

import com.example.demo.model.EventRecord;
import com.example.demo.service.EventRecordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRecordServiceImpl implements EventRecordService {

    private final List<EventRecord> events = new ArrayList<>();

    @Override
    public EventRecord createEvent(EventRecord event) {
        events.add(event);
        return event;
    }

    @Override
    public EventRecord updateEvent(EventRecord updatedEvent) {
        Optional<EventRecord> existingEventOpt = events.stream()
                .filter(e -> e.getId().equals(updatedEvent.getId()))
                .findFirst();

        if (existingEventOpt.isPresent()) {
            EventRecord existingEvent = existingEventOpt.get();
            existingEvent.setEventCode(updatedEvent.getEventCode());
            existingEvent.setName(updatedEvent.getName());
            existingEvent.setEventDate(updatedEvent.getEventDate());
            existingEvent.setBasePrice(updatedEvent.getBasePrice());
            existingEvent.setTotalSeats(updatedEvent.getTotalSeats());
            existingEvent.setRemainingSeats(updatedEvent.getRemainingSeats());
            existingEvent.setActive(updatedEvent.getActive());
            return existingEvent;
        } else {
            throw new IllegalArgumentException("EventRecord not found: " + updatedEvent.getId());
        }
    }

    @Override
    public List<EventRecord> getAllEvents() {
        return new ArrayList<>(events);
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
    public void updateEventStatus(Long id, boolean active) {
        Optional<EventRecord> eventOpt = events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        eventOpt.ifPresent(e -> e.setActive(active));
    }

    @Override
    public void deleteEvent(Long id) {
        events.removeIf(e -> e.getId().equals(id));
    }
}
