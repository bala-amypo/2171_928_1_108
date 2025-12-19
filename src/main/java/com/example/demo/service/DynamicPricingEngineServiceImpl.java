package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔴 REQUIRED
public class DynamicPricingEngineServiceImpl
        implements DynamicPricingEngineService {

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        return new DynamicPriceRecord();
    }

    @Override
    public DynamicPriceRecord getLatestPrice(Long eventId) {
        return null;
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return List.of();
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return List.of();
    }
}
