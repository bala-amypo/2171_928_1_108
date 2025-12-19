package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.List;

public interface PriceAdjustmentLogService {

    void logAdjustment(PriceAdjustmentLog log); // save a new adjustment

    List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId); // get logs by event

    List<PriceAdjustmentLog> getAllAdjustments(); // get all logs
}
