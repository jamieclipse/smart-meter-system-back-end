package com.ddes.smart_meter_system_back_end.bill;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    private String clientId;
    @Getter
    private double billAmount;

    public Bill(String clientId, double billAmount) {
        this.clientId = clientId;
        this.billAmount = billAmount;
    }
    
}