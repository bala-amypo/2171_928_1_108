package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;

import java.util.Optional;

public interface SeatInventoryService {
    void updateRemainingSeats(Long eventId, Integer seatsSold);
    Optional<SeatInventoryRecord> getSeatInventoryByEventId(Long eventId);
}
