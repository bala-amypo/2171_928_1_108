package com.example.demo.service;

import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    @Autowired
    private PricingRuleRepository pricingRuleRepository;

    @Override
    public PricingRule createRule(PricingRule rule) {
        return pricingRuleRepository.save(rule);
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule rule) {
        PricingRule existing = pricingRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PricingRule not found with id " + id));

        // ✅ MATCHES YOUR ENTITY EXACTLY
        existing.setRuleCode(rule.getRuleCode());
        existing.setMinRemainingSeats(rule.getMinRemainingSeats());
        existing.setMaxRemainingSeats(rule.getMaxRemainingSeats());
        existing.setDaysBeforeEvent(rule.getDaysBeforeEvent());
        existing.setPriceMultiplier(rule.getPriceMultiplier());
        existing.setActive(rule.getActive());

        return pricingRuleRepository.save(existing);
    }

    @Override
    public PricingRule getRuleById(Long id) {
        return pricingRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PricingRule not found with id " + id));
    }

    @Override
    public List<PricingRule> getAllRules() {
        return pricingRuleRepository.findAll();
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return pricingRuleRepository.findByActiveTrue();
    }

    @Override
    public void deleteRule(Long id) {
        pricingRuleRepository.deleteById(id);
    }
}
