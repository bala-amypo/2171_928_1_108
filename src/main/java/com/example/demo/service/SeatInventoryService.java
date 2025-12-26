package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;

public interface SeatInventoryService {
    SeatInventoryRecord createInventory(SeatInventoryRecord record);
    SeatInventoryRecord getByEventId(Long eventId);
    SeatInventoryRecord updateRemainingSeats(Long eventId, Integer seats);
}
