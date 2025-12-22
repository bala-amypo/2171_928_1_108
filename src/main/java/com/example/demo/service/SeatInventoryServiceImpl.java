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
        return repository.save(inventory); // ✅ SAVE TO DB
    }

    @Override
    public SeatInventoryRecord updateRemainingSeats(Long eventId, Integer remainingSeats) {

        SeatInventoryRecord inventory =
                repository.findByEventId(eventId).orElse(null);

        if (inventory != null) {
            inventory.setRemainingSeats(remainingSeats);
            return repository.save(inventory); // ✅ UPDATE DB
        }

        return null;
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        return repository.findByEventId(eventId).orElse(null); // ✅ DB FETCH
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return repository.findAll(); // ✅ DB FETCH
    }
}
