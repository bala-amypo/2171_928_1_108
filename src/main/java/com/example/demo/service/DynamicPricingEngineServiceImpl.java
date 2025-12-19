package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service   // 🔴 REQUIRED
public class DynamicPricingEngineServiceImpl
        implements DynamicPricingEngineService {

    private final List<DynamicPriceRecord> history = new ArrayList<>();

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setPrice(100.0); // dummy value
        record.setCalculatedAt(LocalDateTime.now());

        history.add(record);
        return record;
    }

    @Override
    public DynamicPriceRecord getLatestPrice(Long eventId) {
        for (int i = history.size() - 1; i >= 0; i--) {
            if (history.get(i).getEventId().equals(eventId)) {
                return history.get(i);
            }
        }
        return null;
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        List<DynamicPriceRecord> result = new ArrayList<>();
        for (DynamicPriceRecord record : history) {
            if (record.getEventId().equals(eventId)) {
                result.add(record);
            }
        }
        return result;
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return history;
    }
}
