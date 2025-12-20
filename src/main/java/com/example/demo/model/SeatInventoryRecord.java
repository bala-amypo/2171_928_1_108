package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SeatInventoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", insertable = false, updatable = false)
    private EventRecord event;

    private Integer totalSeats;
    private Integer remainingSeats;

    // getters & setters
}
