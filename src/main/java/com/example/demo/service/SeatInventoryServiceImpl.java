package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    @Autowired
    private SeatInventoryRecordRepository repository;

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        Optional<SeatInventoryRecord> record = repository.findById(eventId);
        return record.orElse(null);
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return repository.findAll();
    }

    @Override
    public void updateRemainingSeats(Long eventId, Integer seats) {
        Optional<SeatInventoryRecord> recordOpt = repository.findById(eventId);
        if (recordOpt.isPresent()) {
            SeatInventoryRecord record = recordOpt.get();
            record.setRemainingSeats(seats);
            repository.save(record);
        }
    }
}
