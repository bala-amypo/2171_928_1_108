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
        return repository.save(event); // ✅ DB SAVE
    }

    @Override
    public EventRecord getEventById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public EventRecord getEventByCode(String eventCode) {
        return repository.findByEventCode(eventCode);
    }

    @Override
    public List<EventRecord> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public void updateEventStatus(Long id, boolean active) {
        EventRecord event = repository.findById(id).orElse(null);
        if (event != null) {
            event.setActive(active);
            repository.save(event);
        }
    }
}
