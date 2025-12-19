package com.example.demo.service;

import java.util.List;
import com.example.demo.model.PriceAdjustmentLog;

public interface PriceAdjustmentLogService {
    List<PriceAdjustmentLog> getAllLogs();
    PriceAdjustmentLog saveLog(PriceAdjustmentLog log);
}
