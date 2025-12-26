package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final DynamicPriceRecordRepository dynamicPriceRepo;

    public DynamicPricingEngineServiceImpl(DynamicPriceRecordRepository dynamicPriceRepo) {
        this.dynamicPriceRepo = dynamicPriceRepo;
    }

    @Override
    public Map<Long, Double> getAllDynamicPrices() {
        List<DynamicPriceRecord> records = dynamicPriceRepo.findAll();
        Map<Long, Double> result = new HashMap<>();
        for (DynamicPriceRecord record : records) {
            result.put(record.getEventId(), record.getPrice());
        }
        return result;
    }
}
