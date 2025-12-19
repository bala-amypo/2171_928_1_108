package com.example.demo.service.impl;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.service.PriceAdjustmentLogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceAdjustmentLogServiceImpl implements PriceAdjustmentLogService {

    private final List<PriceAdjustmentLog> logList = new ArrayList<>();

    @Override
    public void logAdjustment(PriceAdjustmentLog log) {
        logList.add(log);
    }

    @Override
    public List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId) {
        return logList.stream()
                .filter(log -> log.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceAdjustmentLog> getAllAdjustments() {
        return new ArrayList<>(logList);
    }
}
