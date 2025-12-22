package com.example.demo.validation;

import com.example.demo.model.EventRecord;
import com.example.demo.model.SeatInventoryRecord;
import org.springframework.stereotype.Component;

/**
 * Central validation class for Events, Seat Inventories, and Price Adjustments.
 */
@Component
public class Validation {

    // ---------------- Event validation ----------------
    public void validateEvent(EventRecord event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }

        if (event.getEventCode() == null || event.getEventCode().isBlank()) {
            throw new IllegalArgumentException("Event code is required");
        }

        if (event.getEventName() == null || event.getEventName().isBlank()) {
            throw new IllegalArgumentException("Event name is required");
        }
    }

    // ---------------- Seat Inventory validation ----------------
    public void validateSeatInventory(SeatInventoryRecord inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Seat inventory cannot be null");
        }

        if (inventory.getTotalSeats() == null || inventory.getTotalSeats() <= 0) {
            throw new IllegalArgumentException("Total seats must be greater than zero");
        }

        if (inventory.getRemainingSeats() == null || inventory.getRemainingSeats() < 0) {
            throw new IllegalArgumentException("Remaining seats cannot be negative");
        }

        if (inventory.getRemainingSeats() > inventory.getTotalSeats()) {
            throw new IllegalArgumentException("Remaining seats cannot exceed total seats");
        }
    }

    // ---------------- Price adjustment validation ----------------
    public void validatePriceChange(Double oldPrice, Double newPrice) {
        if (oldPrice == null || newPrice == null) {
            throw new IllegalArgumentException("Price values cannot be null");
        }

        if (oldPrice < 0 || newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
