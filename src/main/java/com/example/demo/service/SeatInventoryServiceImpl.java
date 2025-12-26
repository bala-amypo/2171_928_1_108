package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository repository;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        List<SeatInventoryRecord> list = repository.findByEventId(eventId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return repository.findAll();
    }

    @Override
    public void setRemainingSeats(Long id, Integer remaining) {
        SeatInventoryRecord record = repository.findById(id).orElse(null);
        if (record != null) {
            record.setRemainingSeats(remaining);
            repository.save(record);
        }
    }
}
