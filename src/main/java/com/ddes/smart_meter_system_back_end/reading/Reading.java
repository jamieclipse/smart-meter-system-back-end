package com.ddes.smart_meter_system_back_end.reading;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String meterId;
    @Getter
    @Setter
    private String readingTimestamp;
    @Getter
    @Setter
    private double readingValue;

    public Reading() {
    }

    public Reading(String meterId, String readingTimestamp, double readingValue) {
        this.meterId = meterId;
        this.readingTimestamp = readingTimestamp;
        this.readingValue = readingValue;
    }
    
}