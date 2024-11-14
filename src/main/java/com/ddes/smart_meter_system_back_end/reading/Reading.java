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
    private double value;

    public Reading(String meterId, double value) {
        this.meterId = meterId;
        this.value = value;
    }
    
}