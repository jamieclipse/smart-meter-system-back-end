package com.ddes.smart_meter_system_back_end;

import jakarta.persistence.*;

@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meterId;
    private String readingTimestamp;
    private double readingValue;

    public Reading() {
    }

    public Reading(String meterId, String readingTimestamp, double readingValue) {
        this.meterId = meterId;
        this.readingTimestamp = readingTimestamp;
        this.readingValue = readingValue;
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

    public String getReadingTimestamp() {
        return readingTimestamp;
    }

    public void setReadingTimestamp(String readingTimestamp) {
        this.readingTimestamp = readingTimestamp;
    }

    public double getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(double readingValue) {
        this.readingValue = readingValue;
    }
    
}