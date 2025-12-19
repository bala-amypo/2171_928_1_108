package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository inventoryRepository;
    private final EventRecordRepository eventRepository;

    public SeatInventoryServiceImpl(
            SeatInventoryRecordRepository inventoryRepository,
            EventRecordRepository eventRepository) {
        this.inventoryRepository = inventoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord inventory) {

        if (!eventRepository.existsById(inventory.getEventId())) {
            throw new RuntimeException("Event does not exist");
        }
        return inventoryRepository.save(inventory);
    }

    @Override
    public SeatInventoryRecord updateRemainingSeats(Long eventId, Integer remainingSeats) {
        SeatInventoryRecord record = inventoryRepository.findByEventId(eventId);
        record.setRemainingSeats(remainingSeats);
        return inventoryRepository.save(record);
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        return inventoryRepository.findByEventId(eventId);
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return inventoryRepository.findAll();
    }
}
