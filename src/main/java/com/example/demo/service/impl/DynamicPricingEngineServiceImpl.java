DynamicPricingEngineServiceImpl.java (FIXED)
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepository;
    private final SeatInventoryRecordRepository inventoryRepository;
    private final PricingRuleRepository ruleRepository;
    private final DynamicPriceRecordRepository priceRepository;
    private final PriceAdjustmentLogRepository logRepository;

    // 🔹 REQUIRED constructor order (DO NOT CHANGE)
    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepository,
            SeatInventoryRecordRepository inventoryRepository,
            PricingRuleRepository ruleRepository,
            DynamicPriceRecordRepository priceRepository,
            PriceAdjustmentLogRepository logRepository) {

        this.eventRepository = eventRepository;
        this.inventoryRepository = inventoryRepository;
        this.ruleRepository = ruleRepository;
        this.priceRepository = priceRepository;
        this.logRepository = logRepository;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException("Event is not active"));

        if (!Boolean.TRUE.equals(event.getActive())) {
            throw new BadRequestException("Event is not active");
        }

        SeatInventoryRecord inventory = inventoryRepository.findByEventId(eventId)
                .orElseThrow(() -> new BadRequestException("Seat inventory not found"));

        double finalPrice = event.getBasePrice();

        List<PricingRule> rules = ruleRepository.findByActiveTrue();

        for (PricingRule rule : rules) {
            if (rule.getPriceMultiplier() == null || rule.getPriceMultiplier() <= 0) {
                throw new BadRequestException("Price multiplier must be > 0");
            }
            finalPrice = finalPrice * rule.getPriceMultiplier();
        }

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(finalPrice);
        record.setAppliedRuleCodes("AUTO");

        return priceRepository.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRepository.findByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public DynamicPriceRecord getLatestPrice(Long eventId) {
        return priceRepository.findFirstByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRepository.findAll();
    }
}