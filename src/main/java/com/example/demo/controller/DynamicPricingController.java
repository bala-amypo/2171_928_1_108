package com.example.demo.controller;

import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService service;

    public DynamicPricingController(DynamicPricingEngineService service) {
        this.service = service;
    }

    @GetMapping("/{eventId}")
    public Double price(@PathVariable Long eventId) {
        return service.computeDynamicPrice(eventId);
    }

    @GetMapping
    public Map<Long, Double> allPrices() {
        return service.getAllDynamicPrices();
    }
}
