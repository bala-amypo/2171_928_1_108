package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository seatInventoryRepo;
    private final EventRecordRepository eventRepo;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository seatInventoryRepo,
                                    EventRecordRepository eventRepo) {
        this.seatInventoryRepo = seatInventoryRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord record) {
        return seatInventoryRepo.save(record);
    }

    @Override
    public Optional<SeatInventoryRecord> getInventoryByEvent(Long eventId) {
        // If your repository returns List, convert to Optional
        return seatInventoryRepo.findByEventId(eventId)
                .stream()
                .findFirst();
    }

    @Override
    public void updateRemainingSeats(Long eventId, Integer seats) {
        Optional<SeatInventoryRecord> recordOpt = getInventoryByEvent(eventId);
        if (recordOpt.isPresent()) {
            SeatInventoryRecord record = recordOpt.get();
            record.setRemainingSeats(seats);
            seatInventoryRepo.save(record);
        }
    }
}
