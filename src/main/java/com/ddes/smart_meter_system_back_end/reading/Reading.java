package com.ddes.smart_meter_system_back_end.reading;

import java.time.LocalDateTime;
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
    private LocalDateTime timestamp;
    @Getter
    @Setter
    private double value;

    public Reading() {
    }

    public Reading(String meterId, LocalDateTime timestamp, double value) {
        this.meterId = meterId;
        this.timestamp = timestamp;
        this.value = value;
    }
    
}