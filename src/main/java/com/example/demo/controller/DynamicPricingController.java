package com.example.demo.controller;

import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
public class DynamicPricingController {

    @Autowired
    private DynamicPricingEngineService service;

    @GetMapping("/compute/{eventId}")
    public Double computeDynamicPrice(@PathVariable Long eventId) {
        return service.computeDynamicPrice(eventId);
    }

    @GetMapping("/all")
    public Object getAllDynamicPrices() {
        return service.getAllDynamicPrices();
    }
}
