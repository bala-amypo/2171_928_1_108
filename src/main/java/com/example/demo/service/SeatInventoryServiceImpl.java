package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository repository;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord inventory) {
        return repository.save(inventory);
    }

    @Override
    public void updateRemainingSeats(Long eventId, Integer remainingSeats) {
        List<SeatInventoryRecord> list = repository.findByEventId(eventId);
        if (!list.isEmpty()) {
            SeatInventoryRecord record = list.get(0);
            record.setRemainingSeats(remainingSeats);
            repository.save(record);
        }
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
}
