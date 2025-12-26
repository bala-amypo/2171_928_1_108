package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl
        implements DynamicPricingEngineService {

    private final DynamicPriceRecordRepository repo;

    public DynamicPricingEngineServiceImpl(
            DynamicPriceRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(100.0);
        record.setAppliedRuleCodes("NO_RULE");
        record.setComputedAt(LocalDateTime.now());
        return repo.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return repo.findAll();
    }
}
