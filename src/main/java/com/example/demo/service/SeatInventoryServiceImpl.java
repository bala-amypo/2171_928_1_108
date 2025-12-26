package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import org.springframework.stereotype.Service;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    @Override
    public void updateRemainingSeats(Long eventId, Integer remainingSeats) {
        SeatInventoryRecord record = new SeatInventoryRecord();
        record.setEventId(eventId);
        record.setRemainingSeats(remainingSeats);
    }
}
