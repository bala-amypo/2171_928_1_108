package com.example.demo.service.impl;

import com.example.demo.model.EventRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.EventRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRecordServiceImpl implements EventRecordService {

    private final EventRecordRepository repository;

    public EventRecordServiceImpl(EventRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventRecord createEvent(EventRecord event) {
        return repository.save(event);
    }

    @Override
    public EventRecord getEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public EventRecord getEventByCode(String eventCode) {
        return repository.findAll().stream()
                .filter(e -> e.getEventCode().equals(eventCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<EventRecord> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public void updateEventStatus(Long id, boolean active) {
        EventRecord event = getEventById(id);
        event.setActive(active);
        repository.save(event);
    }
}
