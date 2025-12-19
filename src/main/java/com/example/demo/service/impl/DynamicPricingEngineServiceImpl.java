package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.service.DynamicPricingEngineService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final List<PricingRule> pricingRules;
    private final List<EventRecord> events; // Events must be injected or managed
    private final List<DynamicPriceRecord> priceHistory = new ArrayList<>();

    public DynamicPricingEngineServiceImpl(List<PricingRule> pricingRules, List<EventRecord> events) {
        this.pricingRules = pricingRules;
        this.events = events;
    }

    private double calculateDynamicPrice(EventRecord event) {
        double finalPrice = event.getBasePrice();
        LocalDate today = LocalDate.now();

        for (PricingRule rule : pricingRules) {
            if (!rule.getActive()) continue;

            long daysBeforeEvent = ChronoUnit.DAYS.between(today, event.getEventDate());

            boolean seatCondition = event.getRemainingSeats() >= rule.getMinRemainingSeats()
                    && event.getRemainingSeats() <= rule.getMaxRemainingSeats();

            boolean dayCondition = daysBeforeEvent <= rule.getDaysBeforeEvent();

            if (seatCondition && dayCondition) {
                finalPrice *= rule.getPriceMultiplier();
            }
        }

        return finalPrice;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        Optional<EventRecord> optEvent = events.stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst();

        if (optEvent.isEmpty()) {
            throw new IllegalArgumentException("Event not found: " + eventId);
        }

        EventRecord event = optEvent.get();
        double price = calculateDynamicPrice(event);

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(event.getId());
        record.setComputedPrice(price);
        record.setAppliedRuleCodes(""); // Optional: store applied rule codes
        record.setComputedAt(LocalDateTime.now());

        priceHistory.add(record);
        return record;
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        List<DynamicPriceRecord> history = new ArrayList<>();
        for (DynamicPriceRecord record : priceHistory) {
            if (record.getEventId().equals(eventId)) {
                history.add(record);
            }
        }
        return history;
    }

    @Override
    public DynamicPriceRecord getLatestPrice(Long eventId) {
        return priceHistory.stream()
                .filter(r -> r.getEventId().equals(eventId))
                .reduce((first, second) -> second)
                .orElse(null);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return new ArrayList<>(priceHistory);
    }
}
