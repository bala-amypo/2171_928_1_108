package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAdjustmentLogServiceImpl
        implements PriceAdjustmentLogService {

    @Autowired
    private PriceAdjustmentLogRepository repository;

    @Override
    public PriceAdjustmentLog saveLog(PriceAdjustmentLog log) {
        return repository.save(log);
    }

    @Override
    public List<PriceAdjustmentLog> getLogsByEventId(Long eventId) {
        return repository.findByEventId(eventId);
    }
}
