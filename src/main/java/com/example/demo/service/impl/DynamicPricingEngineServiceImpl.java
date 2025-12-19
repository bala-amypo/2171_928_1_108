package com.example.demo.service.impl;

import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.service.DynamicPricingEngineService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final List<PricingRule> pricingRules;

    public DynamicPricingEngineServiceImpl(List<PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
    }

    @Override
    public double calculateDynamicPrice(EventRecord event, int remainingSeats) {
        double basePrice = event.getBasePrice();
        double finalPrice = basePrice;

        LocalDate today = LocalDate.now();

        for (PricingRule rule : pricingRules) {
            if (!rule.getActive()) continue;

            long daysBeforeEvent = ChronoUnit.DAYS.between(today, event.getEventDate());

            boolean seatCondition = remainingSeats >= rule.getMinRemainingSeats() &&
                                    remainingSeats <= rule.getMaxRemainingSeats();

            boolean dayCondition = daysBeforeEvent <= rule.getDaysBeforeEvent();

            if (seatCondition && dayCondition) {
                finalPrice *= rule.getPriceMultiplier();
            }
        }

        return finalPrice;
    }

    @Override
    public List<Double> getAllComputedPrices(List<EventRecord> events) {
        List<Double> prices = new ArrayList<>();
        for (EventRecord event : events) {
            // Assuming full capacity calculation (example: event.getTotalSeats())
            prices.add(calculateDynamicPrice(event, event.getRemainingSeats()));
        }
        return prices;
    }
}
