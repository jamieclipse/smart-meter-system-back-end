package com.ddes.smart_meter_system_back_end.bill;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String clientId;
    @Getter
    private Double amount;

    protected Bill() {}
    
    public Bill(String clientId, double amount) {
        this.clientId = clientId;
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return String.format(
    "Bill[id=%d, clientId='%s', amount='%s']",
            id, clientId, amount);
    }
}