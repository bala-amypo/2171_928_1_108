package com.example.demo.service;

import com.example.demo.model.EventRecord;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.model.PricingRule;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidationUtil {

    public static boolean validateEvent(EventRecord event) {
        return event != null && event.getBasePrice() != null && event.getEventDate() != null;
    }

    public static boolean validateInventory(SeatInventoryRecord inventory) {
        return inventory != null && inventory.getTotalSeats() != null;
    }

    public static boolean validatePricingRule(PricingRule rule, EventRecord event, SeatInventoryRecord inventory) {
        if (rule == null || event == null || inventory == null) return false;

        double basePrice = event.getBasePrice();
        LocalDate eventDate = event.getEventDate();
        int totalSeats = inventory.getTotalSeats();

        return basePrice > 0 &&
               !eventDate.isBefore(LocalDate.now()) &&
               totalSeats > 0 &&
               rule.getPriceMultiplier() != null &&
               rule.getMinRemainingSeats() != null &&
               rule.getMaxRemainingSeats() != null &&
               rule.getDaysBeforeEvent() != null;
    }
}
