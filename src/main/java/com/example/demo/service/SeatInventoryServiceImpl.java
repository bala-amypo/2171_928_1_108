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
    public SeatInventoryRecord updateRemainingSeats(Long eventId, Integer remainingSeats) {
        Optional<SeatInventoryRecord> optionalInventory = inventories.stream()
                .filter(inv -> inv.getEventId().equals(eventId))
                .findFirst();

        if (optionalInventory.isPresent()) {
            SeatInventoryRecord inventory = optionalInventory.get();
            inventory.setRemainingSeats(remainingSeats); // update the seats
            return inventory;
        }

        return null; // or throw exception if inventory not found
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        return inventories.stream()
                .filter(inv -> inv.getEventId().equals(eventId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return inventories;
    }
}
