package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.model.SeatInventoryRecord;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidationUtil {

    private ValidationUtil() {
        // utility class
    }

    // 1️⃣ Validate Event
    public static void validateEvent(EventRecord event) {
        if (event == null) {
            throw new ValidationException("Event cannot be null");
        }

        if (event.basePrice() == null || event.basePrice().doubleValue() <= 0) {
            throw new ValidationException("Base price must be greater than zero");
        }

        if (event.eventDate() == null || event.eventDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Event date must be in the future");
        }
    }

    // 2️⃣ Validate Seat Inventory
    public static void validateSeatInventory(SeatInventoryRecord inventory) {
        if (inventory == null) {
            throw new ValidationException("Seat inventory cannot be null");
        }

        if (inventory.totalSeats() == null || inventory.totalSeats() <= 0) {
            throw new ValidationException("Total seats must be greater than zero");
        }

        if (inventory.remainingSeats() == null || inventory.remainingSeats() < 0) {
            throw new ValidationException("Remaining seats cannot be negative");
        }
    }

    // 3️⃣ Validate Pricing Rule
    public static void validatePricingRule(PricingRule rule) {
        if (rule == null) {
            throw new ValidationException("Pricing rule cannot be null");
        }

        if (rule.priceMultiplier() == null || rule.priceMultiplier() <= 0) {
            throw new ValidationException("Price multiplier must be greater than zero");
        }

        if (rule.minRemainingSeats() < 0 || rule.maxRemainingSeats() < 0) {
            throw new ValidationException("Seat limits cannot be negative");
        }

        if (rule.minRemainingSeats() > rule.maxRemainingSeats()) {
            throw new ValidationException("Min remaining seats cannot exceed max remaining seats");
        }

        if (rule.daysBeforeEvent() < 0) {
            throw new ValidationException("Days before event cannot be negative");
        }
    }

    // 4️⃣ Validate Pricing Applicability
    public static boolean isRuleApplicable(
            PricingRule rule,
            EventRecord event,
            SeatInventoryRecord inventory
    ) {
        long daysUntilEvent =
                ChronoUnit.DAYS.between(LocalDate.now(), event.eventDate());

        return inventory.remainingSeats() >= rule.minRemainingSeats()
                && inventory.remainingSeats() <= rule.maxRemainingSeats()
                && daysUntilEvent <= rule.daysBeforeEvent();
    }
}
