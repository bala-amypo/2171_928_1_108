package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl
        implements DynamicPricingEngineService {

    private final DynamicPriceRecordRepository dynamicRepo;
    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository inventoryRepo;
    private final PricingRuleRepository ruleRepo;

    public DynamicPricingEngineServiceImpl(
            DynamicPriceRecordRepository dynamicRepo,
            EventRecordRepository eventRepo,
            SeatInventoryRecordRepository inventoryRepo,
            PricingRuleRepository ruleRepo) {

        this.dynamicRepo = dynamicRepo;
        this.eventRepo = eventRepo;
        this.inventoryRepo = inventoryRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.isActive()) {
            throw new RuntimeException("Event is inactive");
        }

        SeatInventoryRecord inventory = inventoryRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        double finalPrice = 100.0;

        List<PricingRule> rules = ruleRepo.findByActiveTrue();
        StringBuilder appliedRules = new StringBuilder();

        for (PricingRule rule : rules) {
            appliedRules.append(rule.getRuleCode()).append(",");
        }

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(finalPrice);
        record.setAppliedRuleCodes(
                appliedRules.length() > 0
                        ? appliedRules.substring(0, appliedRules.length() - 1)
                        : "NO_RULE");
        record.setComputedAt(LocalDateTime.now());

        return dynamicRepo.save(record);
    }

    @Override
    public DynamicPriceRecord getLatestPrice(Long eventId) {
        return dynamicRepo
                .findFirstByEventIdOrderByComputedAtDesc(eventId)
                .orElse(null);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return dynamicRepo.findByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return dynamicRepo.findAll();
    }
}
