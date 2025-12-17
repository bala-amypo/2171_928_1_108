package com.example.demo.controller;
import java.util.Collection;
import org.springframework.Beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Studentity;
import com.example.demo.service.Studservice;

@RestController
@RequestMapping("/student")
public class Studctl{
    @Autowired
    private Studservice ser;
    
    @PostMapping("/add")
    p
}