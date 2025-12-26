package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository seatInventoryRepo;
    private final PricingRuleRepository pricingRuleRepo;
    private final DynamicPriceRecordRepository dynamicPriceRepo;
    private final PriceAdjustmentLogRepository priceLogRepo;

    public DynamicPricingEngineServiceImpl(EventRecordRepository eventRepo,
                                           SeatInventoryRecordRepository seatInventoryRepo,
                                           PricingRuleRepository pricingRuleRepo,
                                           DynamicPriceRecordRepository dynamicPriceRepo,
                                           PriceAdjustmentLogRepository priceLogRepo) {
        this.eventRepo = eventRepo;
        this.seatInventoryRepo = seatInventoryRepo;
        this.pricingRuleRepo = pricingRuleRepo;
        this.dynamicPriceRepo = dynamicPriceRepo;
        this.priceLogRepo = priceLogRepo;
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return dynamicPriceRepo.findByEventId(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return dynamicPriceRepo.findAll();
    }

    // Add other methods as per interface
}
