package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository seatInventoryRepository;
    private final EventRecordRepository eventRepository;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository seatInventoryRepository, EventRecordRepository eventRepository) {
        this.seatInventoryRepository = seatInventoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord record) {
        return seatInventoryRepository.save(record);
    }

    @Override
    public Optional<SeatInventoryRecord> getInventoryByEvent(Long eventId) {
        return seatInventoryRepository.findByEventId(eventId);
    }

    // Add other methods if required
}
