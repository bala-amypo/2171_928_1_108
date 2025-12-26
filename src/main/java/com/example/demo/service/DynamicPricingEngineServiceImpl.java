package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    @Override
    public Double computeDynamicPrice(Long eventId) {
        return 100.0;
    }

    @Override
    public Map<Long, Double> getAllDynamicPrices() {
        return new HashMap<>();
    }
}
