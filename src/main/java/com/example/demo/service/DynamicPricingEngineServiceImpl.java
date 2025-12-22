package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
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

        // 1️⃣ Fetch event
        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.isActive()) {
            throw new RuntimeException("Event is inactive");
        }

        // 2️⃣ Fetch inventory
        SeatInventoryRecord inventory = inventoryRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        // 3️⃣ Base price
        double finalPrice = event.getBasePrice();
        StringBuilder appliedRules = new StringBuilder();

        // 4️⃣ Apply active rules
        List<PricingRule> rules = ruleRepo.findByActiveTrue();

        for (PricingRule rule : rules) {
            if (inventory.getRemainingSeats() <= rule.getSeatThreshold()) {
                finalPrice = finalPrice * rule.getMultiplier();
                appliedRules.append(rule.getRuleCode()).append(",");
            }
        }

        // 5️⃣ Save price record
        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(finalPrice);
        record.setAppliedRuleCodes(appliedRules.toString());
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
