package com.example.demo.controller;

import com.example.demo.model.EventRecord;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventRecordController {

    @PostMapping
    public EventRecord create(@RequestBody EventRecord event) {
        return event;
    }
}
