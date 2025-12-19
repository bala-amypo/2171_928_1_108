package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepository;
    private final PricingRuleRepository pricingRuleRepository;
    private final DynamicPriceRecordRepository priceRecordRepository;

    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepository,
            PricingRuleRepository pricingRuleRepository,
            DynamicPriceRecordRepository priceRecordRepository) {
        this.eventRepository = eventRepository;
        this.pricingRuleRepository = pricingRuleRepository;
        this.priceRecordRepository = priceRecordRepository;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        double finalPrice = event.getBasePrice();
        LocalDate today = LocalDate.now();

        List<PricingRule> rules = pricingRuleRepository.getActiveRules();

        for (PricingRule rule : rules) {

            long daysBeforeEvent =
                    ChronoUnit.DAYS.between(today, event.getEventDate());

            boolean seatCondition =
                    event.getRemainingSeats() >= rule.getMinRemainingSeats()
                            && event.getRemainingSeats() <= rule.getMaxRemainingSeats();

            boolean dayCondition =
                    daysBeforeEvent <= rule.getDaysBeforeEvent();

            if (seatCondition && dayCondition) {
                finalPrice *= rule.getPriceMultiplier();
            }
        }

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(finalPrice);
        record.setComputedAt(LocalDateTime.now());

        return priceRecordRepository.save(record);
    }

    @Override
    public DynamicPriceRecord getLatestPrice(Long eventId) {
        return priceRecordRepository.findTopByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRecordRepository.findByEventId(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRecordRepository.findAll();
    }
}
