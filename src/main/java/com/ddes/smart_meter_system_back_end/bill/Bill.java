package com.ddes.smart_meter_system_back_end.bill;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String clientId;
    @Getter
    @Setter
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