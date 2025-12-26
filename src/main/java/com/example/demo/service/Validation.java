package com.example.demo.util;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValidationUtil {

    // ============================
    // EventRecord validation
    // ============================
    public static void validateEvent(EventRecord event) {
        if (event.getEventCode() == null || event.getEventCode().isBlank()) {
            throw new BadRequestException("Event code is required");
        }
        if (event.getBasePrice() <= 0) {
            throw new BadRequestException("Base price must be > 0");
        }
        if (event.getEventDate() == null || event.getEventDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Event date must be in the future");
        }
    }

    // ============================
    // SeatInventoryRecord validation
    // ============================
    public static void validateSeatInventory(SeatInventoryRecord inventory) {
        if (inventory.getTotalSeats() == null || inventory.getTotalSeats() <= 0) {
            throw new BadRequestException("Total seats must be > 0");
        }
        if (inventory.getRemainingSeats() == null) {
            throw new BadRequestException("Remaining seats cannot be null");
        }
        if (inventory.getRemainingSeats() > inventory.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }
    }

    // ============================
    // PricingRule validation
    // ============================
    public static void validatePricingRule(PricingRule rule) {
        if (rule.getRuleCode() == null || rule.getRuleCode().isBlank()) {
            throw new BadRequestException("Rule code is required");
        }
        if (rule.getPriceMultiplier() <= 0) {
            throw new BadRequestException("Price multiplier must be > 0");
        }
        if (rule.getMinRemainingSeats() < 0 || rule.getMaxRemainingSeats() < 0) {
            throw new BadRequestException("Remaining seats cannot be negative");
        }
        if (rule.getMinRemainingSeats() > rule.getMaxRemainingSeats()) {
            throw new BadRequestException("Min remaining seats cannot exceed max remaining seats");
        }
        if (rule.getDaysBeforeEvent() < 0) {
            throw new BadRequestException("Days before event must be >= 0");
        }
    }

    // ============================
    // DynamicPriceRecord validation
    // ============================
    public static void validateDynamicPrice(DynamicPriceRecord priceRecord) {
        if (priceRecord.getEventId() == null) {
            throw new BadRequestException("Event ID is required");
        }
        if (priceRecord.getComputedPrice() < 0) {
            throw new BadRequestException("Computed price must be >= 0");
        }
        if (priceRecord.getComputedAt() == null) {
            throw new BadRequestException("Computed timestamp is required");
        }
    }

    // ============================
    // PriceAdjustmentLog validation
    // ============================
    public static void validatePriceAdjustmentLog(PriceAdjustmentLog log) {
        if (log.getEventId() == null) {
            throw new BadRequestException("Event ID is required");
        }
        if (log.getOldPrice() < 0 || log.getNewPrice() < 0) {
            throw new BadRequestException("Prices must be >= 0");
        }
        if (log.getAdjustedAt() == null) {
            throw new BadRequestException("Adjusted timestamp is required");
        }
    }

    // ============================
    // User validation
    // ============================
    public static void validateUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new BadRequestException("Name is required");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new BadRequestException("Email is invalid");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BadRequestException("Password is required");
        }
        if (user.getRole() == null || user.getRole().isBlank()) {
            throw new BadRequestException("Role is required");
        }
    }
}
