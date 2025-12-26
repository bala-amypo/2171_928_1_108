package com.example.demo.service.impl;

import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    @Override
    public Map<Long, Double> getAllComputedPrices() {
        return new HashMap<>();
    }
}
