package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import org.springframework.stereotype.Service;

@Service
public class SeatInventoryServiceImpl {

    public SeatInventoryRecord createInventory(Long eventId, Integer totalSeats, Integer remainingSeats) {
        SeatInventoryRecord record = new SeatInventoryRecord();
        record.setEventId(eventId);
        record.setTotalSeats(totalSeats);
        record.setRemainingSeats(remainingSeats);
        return record;
    }
}
