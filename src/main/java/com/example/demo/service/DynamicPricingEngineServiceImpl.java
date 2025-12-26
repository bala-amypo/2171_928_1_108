package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    @Autowired
    private DynamicPriceRecordRepository dynamicPriceRepo;

    @Override
    public Map<Long, Double> getAllDynamicPrices() {
        List<DynamicPriceRecord> records = dynamicPriceRepo.findAll();
        // Convert list to Map<eventId, price>
        return records.stream()
                .collect(Collectors.toMap(DynamicPriceRecord::getEventId, DynamicPriceRecord::getPrice));
    }

    @Override
    public Double computeDynamicPrice(Long eventId) {
        List<DynamicPriceRecord> records = dynamicPriceRepo.findByEventId(eventId);
        // Return the first price or 0.0 if not found
        return records.stream()
                .map(DynamicPriceRecord::getPrice)
                .findFirst()
                .orElse(0.0);
    }
}
