package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepository;
    private final SeatInventoryRecordRepository inventoryRepository;
    private final PricingRuleRepository pricingRuleRepository;
    private final DynamicPriceRecordRepository dynamicPriceRepository;
    private final PriceAdjustmentLogRepository priceAdjustmentLogRepository;

    public DynamicPricingEngineServiceImpl(EventRecordRepository eventRepository,
                                           SeatInventoryRecordRepository inventoryRepository,
                                           PricingRuleRepository pricingRuleRepository,
                                           DynamicPriceRecordRepository dynamicPriceRepository,
                                           PriceAdjustmentLogRepository priceAdjustmentLogRepository) {
        this.eventRepository = eventRepository;
        this.inventoryRepository = inventoryRepository;
        this.pricingRuleRepository = pricingRuleRepository;
        this.dynamicPriceRepository = dynamicPriceRepository;
        this.priceAdjustmentLogRepository = priceAdjustmentLogRepository;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        EventRecord event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.isActive()) {
            throw new BadRequestException("Event is not active");
        }

        SeatInventoryRecord inventory = inventoryRepository.findByEventIdOrderByIdDesc(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        double computedPrice = event.getBasePrice();
        String appliedRules = "";

        List<PricingRule> rules = pricingRuleRepository.findByActiveTrue();
        for (PricingRule rule : rules) {
            if (inventory.getRemainingSeats() >= rule.getMinRemainingSeats() &&
                    inventory.getRemainingSeats() <= rule.getMaxRemainingSeats() &&
                    LocalDate.now().until(event.getEventDate()).getDays() <= rule.getDaysBeforeEvent()) {
                computedPrice *= rule.getPriceMultiplier();
                appliedRules += rule.getRuleCode() + ",";
            }
        }

        DynamicPriceRecord previous = dynamicPriceRepository
                .findFirstByEventIdOrderByComputedAtDesc(eventId)
                .orElse(null);

        if (previous == null || previous.getComputedPrice() != computedPrice) {
            PriceAdjustmentLog log = new PriceAdjustmentLog();
            log.setEventId(eventId);
            log.setOldPrice(previous == null ? event.getBasePrice() : previous.getComputedPrice());
            log.setNewPrice(computedPrice);
            log.setAdjustedAt(LocalDateTime.now());
            priceAdjustmentLogRepository.save(log);
        }

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(computedPrice);
        record.setAppliedRuleCodes(appliedRules.isEmpty() ? null : appliedRules.substring(0, appliedRules.length() - 1));
        record.setComputedAt(LocalDateTime.now());

        return dynamicPriceRepository.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return dynamicPriceRepository.findByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return dynamicPriceRepository.findAll();
    }
}
