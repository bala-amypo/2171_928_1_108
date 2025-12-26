package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;

public class ValidationUtil {

    public static boolean isPriceValid(Double price) {
        return price != null && price > 0;
    }

    public static boolean isSeatsAvailable(SeatInventoryRecord inventory, Integer requestedSeats) {
        if (inventory == null || inventory.getRemainingSeats() == null || requestedSeats == null) return false;
        return inventory.getRemainingSeats() >= requestedSeats;
    }
}
