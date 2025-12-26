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

    // ---------------- EVENT VALIDATION ----------------
    public static void validateEvent(EventRecord event) {
        if (event == null) {
            throw new ValidationException("Event cannot be null");
        }

        if (event.getBasePrice() == null || event.getBasePrice() <= 0) {
            throw new ValidationException("Base price must be greater than zero");
        }

        if (event.getEventDate() == null) {
            throw new ValidationException("Event date cannot be null");
        }
    }

    // ---------------- INVENTORY VALIDATION ----------------
    public static void validateInventory(SeatInventoryRecord inventory) {
        if (inventory == null) {
            throw new ValidationException("Seat inventory cannot be null");
        }

        if (inventory.getTotalSeats() == null || inventory.getTotalSeats() <= 0) {
            throw new ValidationException("Total seats must be greater than zero");
        }

        if (inventory.getRemainingSeats() == null || inventory.getRemainingSeats() < 0) {
            throw new ValidationException("Remaining seats cannot be negative");
        }
    }

    // ---------------- PRICING RULE VALIDATION ----------------
    public static void validatePricingRule(PricingRule rule) {
        if (rule == null) {
            throw new ValidationException("Pricing rule cannot be null");
        }

        if (rule.getPriceMultiplier() == null || rule.getPriceMultiplier() <= 0) {
            throw new ValidationException("Price multiplier must be greater than zero");
        }

        if (rule.getMinRemainingSeats() == null || rule.getMaxRemainingSeats() == null) {
            throw new ValidationException("Seat range cannot be null");
        }

        if (rule.getMinRemainingSeats() > rule.getMaxRemainingSeats()) {
            throw new ValidationException("Minimum remaining seats cannot exceed maximum remaining seats");
        }

        if (rule.getDaysBeforeEvent() == null || rule.getDaysBeforeEvent() < 0) {
            throw new ValidationException("Days before event must be zero or positive");
        }
    }

    // ---------------- RULE APPLICABILITY CHECK ----------------
    public static boolean isRuleApplicable(
            PricingRule rule,
            EventRecord event,
            SeatInventoryRecord inventory
    ) {
        validatePricingRule(rule);
        validateEvent(event);
        validateInventory(inventory);

        long daysUntilEvent = ChronoUnit.DAYS.between(
                LocalDate.now(),
                event.getEventDate()
        );

        return inventory.getRemainingSeats() >= rule.getMinRemainingSeats()
                && inventory.getRemainingSeats() <= rule.getMaxRemainingSeats()
                && daysUntilEvent <= rule.getDaysBeforeEvent();
    }
}
