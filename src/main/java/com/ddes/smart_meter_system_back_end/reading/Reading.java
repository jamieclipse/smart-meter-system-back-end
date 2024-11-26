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
    private String clientId;
    @Getter
    private double value;

    public Reading(String clientId, double value) {
        this.clientId = clientId;
        this.value = value;
    }
    
}