package com.ddes.smart_meter_system_back_end.reading;

import org.springframework.stereotype.Service;

@Service
public class ReadingService {

    public double calculateReadingDifference(String clientId, double newReading){
        double initialReading = getInitialReading(clientId);
        double difference = newReading - initialReading; // New reading should always be greater than initial reading
        return difference;

    }

    private double getInitialReading(String clientId){
        return 1000;
    }

    public void storeReading(Reading reading){
        // Store the reading in the database
    }

}
