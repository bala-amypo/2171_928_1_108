package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;

import java.util.List;

public interface PriceAdjustmentLogService {

    PriceAdjustmentLog saveLog(PriceAdjustmentLog log);

    List<PriceAdjustmentLog> getLogsByEventId(Long eventId);
}
