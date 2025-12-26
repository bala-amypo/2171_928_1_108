package com.example.demo.service;

import com.example.demo.model.EventRecord;
import com.example.demo.repository.EventRecordRepository;
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
    public List<EventRecord> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public EventRecord updateEvent(Long id, EventRecord event) {
        EventRecord existing = getEventById(id);
        existing.setEventName(event.getEventName());
        existing.setActive(event.isActive());
        return repository.save(existing);
    }

    @Override
    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}
