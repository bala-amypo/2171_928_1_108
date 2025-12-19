package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.EventRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.EventRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRecordServiceImpl implements EventRecordService {

    private final EventRecordRepository repository;

    // ✅ Constructor Injection (MANDATORY)
    public EventRecordServiceImpl(EventRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventRecord createEvent(EventRecord event) {

        if (repository.existsByEventCode(event.getEventCode())) {
            throw new BadRequestException("Event code already exists");
        }

        if (event.getBasePrice() <= 0) {
            throw new BadRequestException("Base price must be > 0");
        }

        return repository.save(event);
    }

    @Override
    public EventRecord getEventById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public EventRecord getEventByCode(String eventCode) {
        return repository.findByEventCode(eventCode).orElse(null);
    }

    @Override
    public List<EventRecord> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public EventRecord updateEventStatus(Long id, boolean active) {
        EventRecord event = repository.findById(id).orElse(null);
        if (event == null) {
            return null;
        }
        event.setActive(active);
        return repository.save(event);
    }
}
