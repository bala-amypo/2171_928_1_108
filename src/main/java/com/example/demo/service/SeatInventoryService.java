package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SeatInventoryRecord;

import java.util.List;

public interface SeatInventoryService {

    SeatInventoryRecord createInventory(SeatInventoryRecord inventory) throws BadRequestException;

    SeatInventoryRecord getInventoryByEvent(Long eventId);

    List<SeatInventoryRecord> getAllInventories();
}
