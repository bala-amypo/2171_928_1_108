package com.example.demo.service.impl;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import com.example.demo.service.PriceAdjustmentLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAdjustmentLogServiceImpl implements PriceAdjustmentLogService {

    private final PriceAdjustmentLogRepository repository;

    public PriceAdjustmentLogServiceImpl(PriceAdjustmentLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logAdjustment(PriceAdjustmentLog log) {
        repository.save(log); // ✅ SAVES TO DATABASE
    }

    @Override
    public List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId) {
        return repository.findAll()
                .stream()
                .filter(log -> log.getEventId().equals(eventId))
                .toList();
    }

    @Override
    public List<PriceAdjustmentLog> getAllAdjustments() {
        return repository.findAll();
    }
}
