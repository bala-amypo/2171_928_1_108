package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository repository;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord record) {
        return repository.save(record);
    }

    @Override
    public SeatInventoryRecord getByEventId(Long eventId) {
        return repository.findByEventId(eventId);
    }

    @Override
    public SeatInventoryRecord updateRemainingSeats(Long eventId, Integer seats) {
        SeatInventoryRecord record = repository.findByEventId(eventId);
        if (record != null) {
            record.setRemainingSeats(seats);
            return repository.save(record);
        }
        return null;
    }
}
