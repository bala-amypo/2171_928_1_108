package com.example.demo.controller;

import com.example.demo.model.SeatInventoryRecord;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class SeatInventoryController {

    @PutMapping("/{id}")
    public SeatInventoryRecord update(@PathVariable Long id,
                                      @RequestParam Integer seats) {
        SeatInventoryRecord record = new SeatInventoryRecord();
        record.setId(id);
        record.setTotalSeats(seats);
        return record;
    }
}
