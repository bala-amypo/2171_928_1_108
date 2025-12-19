package com.example.demo.controller;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.service.PriceAdjustmentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price-adjustments")
public class PriceAdjustmentLogController {

    @Autowired
    private PriceAdjustmentLogService service;

    // Add a new price adjustment
    @PostMapping("/log")
    public String logAdjustment(@RequestBody PriceAdjustmentLog log) {
        service.logAdjustment(log);
        return "Price adjustment logged successfully";
    }

    // Get adjustments for a specific event
    @GetMapping("/event/{eventId}")
    public List<PriceAdjustmentLog> getAdjustmentsByEvent(@PathVariable Long eventId) {
        return service.getAdjustmentsByEvent(eventId);
    }

    // Get all adjustments
    @GetMapping("/all")
    public List<PriceAdjustmentLog> getAllAdjustments() {
        return service.getAllAdjustments();
    }
}
