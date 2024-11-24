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
    @Setter
    private String clientId;
    @Getter
    @Setter
    private String billTimestamp;
    @Getter
    @Setter
    private double billAmount;
    @Getter
    @Setter
    private double dayRate;

    public Bill(String clientId, String billTimestamp, double billAmount) {
        this.clientId = clientId;
        this.billTimestamp = billTimestamp;
        this.billAmount = billAmount;
    }
    
}