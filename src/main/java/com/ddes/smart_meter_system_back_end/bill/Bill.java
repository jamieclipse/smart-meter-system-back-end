package com.ddes.smart_meter_system_back_end.bill;

import jakarta.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meterId;
    private String billTimestamp;
    private double billAmount;

    public Bill() {
    }

    public Bill(String meterId, String billTimestamp, double billAmount) {
        this.meterId = meterId;
        this.billTimestamp = billTimestamp;
        this.billAmount = billAmount;
    }

    public Long getId() {
        return id;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getBillTimestamp() {
        return billTimestamp;
    }

    public void setBillTimestamp(String billTimestamp) {
        this.billTimestamp = billTimestamp;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

}