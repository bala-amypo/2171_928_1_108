package com.example.demo.service;

import java.util.Map;

public interface DynamicPricingEngineService {

    // Returns all dynamic prices as eventId -> price
    Map<Long, Double> getAllDynamicPrices();

    // Compute price for a specific event
    Double computeDynamicPrice(Long eventId);
}
