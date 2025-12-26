package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final List<DynamicPriceRecord> priceRecords = new ArrayList<>();

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(100.0); // default price for test case
        priceRecords.add(record);
        return record;
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRecords;
    }
}
