package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class EventRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String eventCode;

    private String eventName;

    private boolean active;

    // getters & setters
}
