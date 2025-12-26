package com.example.demo.service;

import java.util.Map;

public interface DynamicPricingEngineService {

    Double computeDynamicPrice(Long eventId);
    Map<Long, Double> getAllDynamicPrices();
}
