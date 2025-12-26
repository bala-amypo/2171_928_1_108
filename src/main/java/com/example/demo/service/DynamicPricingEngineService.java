package com.example.demo.service;

import java.util.Map;

public interface DynamicPricingEngineService {
    Map<Long, Double> getAllDynamicPrices();
}
