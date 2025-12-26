package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class Validation {
    public boolean isValidId(Long id) {
        return id != null && id > 0;
    }
}
