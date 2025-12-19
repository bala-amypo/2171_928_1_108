package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dynamic-pricing")
@Tag(name = "Dynamic Pricing Engine")
public class DynamicPricingController {

    private final DynamicPricingEngineService service;

    public DynamicPricingController(DynamicPricingEngineService service) {
        this.service = service;
    }

    @PostMapping("/compute/{eventId}")
    public DynamicPriceRecord computePrice(@PathVariable Long eventId) {
        return service.computeDynamicPrice(eventId);
    }

    @GetMapping("/latest/{eventId}")
    public DynamicPriceRecord getLatest(@PathVariable Long eventId) {
        return service.getLatestPrice(eventId);
    }

    @GetMapping("/history/{eventId}")
    public List<DynamicPriceRecord> getHistory(@PathVariable Long eventId) {
        return service.getPriceHistory(eventId);
    }

    @GetMapping
    public List<DynamicPriceRecord> getAll() {
        return service.getAllComputedPrices();
    }
}
