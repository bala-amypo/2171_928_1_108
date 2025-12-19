package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.service.PriceAdjustmentLogService;
import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.repository.PriceAdjustmentLogRepository;

@Service
public class PriceAdjustmentLogServiceImpl implements PriceAdjustmentLogService {

    private final PriceAdjustmentLogRepository repository;

    public PriceAdjustmentLogServiceImpl(PriceAdjustmentLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PriceAdjustmentLog> getAllLogs() {
        return repository.findAll();
    }

    @Override
    public PriceAdjustmentLog saveLog(PriceAdjustmentLog log) {
        return repository.save(log);
    }
}
