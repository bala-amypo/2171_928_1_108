package com.example.demo.service.impl;

import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    @Autowired
    private SeatInventoryRecordRepository inventoryRepository;

    @Autowired
    private PricingRuleRepository pricingRuleRepository;

    @Override
    public void computePrice(EventRecord event) {
        List<SeatInventoryRecord> inventories = inventoryRepository.findByEventId(event.getId());
        SeatInventoryRecord inventory = inventories.isEmpty() ? null : inventories.get(0);

        List<PricingRule> rules = pricingRuleRepository.findByActiveTrue();
        for (PricingRule rule : rules) {
            // Example computation logic
            double multiplier = rule.getPriceMultiplier();
            double newPrice = event.getBasePrice() * multiplier;
            // save or apply newPrice logic here
        }
    }
}
