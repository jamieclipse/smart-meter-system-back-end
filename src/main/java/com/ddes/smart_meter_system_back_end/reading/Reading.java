package com.ddes.smart_meter_system_back_end.reading;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String clientId;
    @Getter
    private Double amount;

    protected Reading() {}
    
    public Reading(String clientId, double amount) {
        this.clientId = clientId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format(
    "Reading[id=%d, clientId='%s', amount='%s']",
            id, clientId, amount);
    }
    
}