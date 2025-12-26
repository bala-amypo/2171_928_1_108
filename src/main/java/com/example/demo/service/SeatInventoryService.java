package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import java.util.List;

public interface SeatInventoryService {
    SeatInventoryRecord getInventoryByEvent(Long eventId);
    List<SeatInventoryRecord> getAllInventories();
    void updateRemainingSeats(Long eventId, Integer seats);
}
