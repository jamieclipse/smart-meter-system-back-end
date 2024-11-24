package com.ddes.smart_meter_system_back_end.reading;

public class ReadingService {

    public ReadingService(){
    }

    public double calculateReadingDifference(String clientId, double newReading){
        double initialReading = getInitialReading(clientId);
        double difference = newReading - initialReading;
        return difference;

    }

    private double getInitialReading(String clientId){
        return 1000;
    }

}
