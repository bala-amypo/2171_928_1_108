package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import java.util.List;

public interface SeatInventoryService {

    // Existing methods
    SeatInventoryRecord getInventoryByEvent(Long eventId);
    List<SeatInventoryRecord> getAllInventories();
    void updateRemainingSeats(Long eventId, Integer seats);

    // New methods required by controller
    SeatInventoryRecord createInventory(SeatInventoryRecord record);
}
