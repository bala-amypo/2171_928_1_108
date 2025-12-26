package com.example.demo.controller;

import com.example.demo.model.PriceAdjustmentLog;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class PriceAdjustmentLogController {

    @GetMapping
    public List<PriceAdjustmentLog> getAll() {
        return Collections.emptyList();
    }
}
