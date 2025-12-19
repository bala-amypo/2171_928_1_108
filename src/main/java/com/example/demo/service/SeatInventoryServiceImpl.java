package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final List<SeatInventoryRecord> inventories = new ArrayList<>();

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord inventory) {
        inventories.add(inventory);
        return inventory;
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        Optional<SeatInventoryRecord> inventory = inventories.stream()
                .filter(i -> i.getEventId().equals(eventId))
                .findFirst();

        return inventory.orElse(null); // ✅ FIXED
    }

    @Override
    public void updateRemainingSeats(Long eventId, Integer remainingSeats) {
        SeatInventoryRecord inventory = getInventoryByEvent(eventId);
        if (inventory != null) {
            inventory.setRemainingSeats(remainingSeats);
        }
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return inventories;
    }
}
