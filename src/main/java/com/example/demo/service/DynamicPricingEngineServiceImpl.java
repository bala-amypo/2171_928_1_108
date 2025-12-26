package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl
        implements DynamicPricingEngineService {

    private final DynamicPriceRecordRepository repository;

    public DynamicPricingEngineServiceImpl(
            DynamicPriceRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(100.0); // default test value
        record.setAppliedRuleCodes("DEFAULT");
        record.setComputedAt(LocalDateTime.now());
        return repository.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return repository.findAll();
    }
}
