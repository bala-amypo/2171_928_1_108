package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository inventoryRepository;
    private final EventRecordRepository eventRepository;

    // REQUIRED constructor order
    public SeatInventoryServiceImpl(SeatInventoryRecordRepository inventoryRepository,
                                    EventRecordRepository eventRepository) {
        this.inventoryRepository = inventoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord inventory) {
        if (!eventRepository.existsById(inventory.getEventId())) {
            throw new BadRequestException("Seat inventory not found");
        }
        if (inventory.getRemainingSeats() > inventory.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }
        return inventoryRepository.save(inventory);
    }

    @Override
    public void updateRemainingSeats(Long eventId, Integer remainingSeats) {
        SeatInventoryRecord inventory = getInventoryByEvent(eventId);
        if (remainingSeats > inventory.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }
        inventory.setRemainingSeats(remainingSeats);
        inventoryRepository.save(inventory);
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        return inventoryRepository.findByEventId(eventId)
                .orElseThrow(() -> new BadRequestException("Seat inventory not found"));
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return inventoryRepository.findAll();
    }
}
