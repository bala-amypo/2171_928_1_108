package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository seatInventoryRepo;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository seatInventoryRepo) {
        this.seatInventoryRepo = seatInventoryRepo;
    }

    @Override
    public void updateRemainingSeats(Long eventId, Integer seatsSold) {
        List<SeatInventoryRecord> records = seatInventoryRepo.findByEventId(eventId);
        for (SeatInventoryRecord record : records) {
            record.setRemainingSeats(record.getRemainingSeats() - seatsSold);
            seatInventoryRepo.save(record);
        }
    }

    @Override
    public Optional<SeatInventoryRecord> getSeatInventoryByEventId(Long eventId) {
        List<SeatInventoryRecord> records = seatInventoryRepo.findByEventId(eventId);
        if (records.isEmpty()) return Optional.empty();
        return Optional.of(records.get(0));
    }
}
