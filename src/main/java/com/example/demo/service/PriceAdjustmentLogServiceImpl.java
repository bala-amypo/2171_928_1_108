package com.example.demo.service.impl;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import com.example.demo.service.PriceAdjustmentLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAdjustmentLogServiceImpl implements PriceAdjustmentLogService {

    private final PriceAdjustmentLogRepository logRepo;

    public PriceAdjustmentLogServiceImpl(PriceAdjustmentLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public List<PriceAdjustmentLog> getAllAdjustments() {
        return logRepo.findAll();
    }
}
