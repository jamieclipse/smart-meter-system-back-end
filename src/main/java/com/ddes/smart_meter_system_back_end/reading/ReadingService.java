package com.ddes.smart_meter_system_back_end.reading;

public class ReadingService {

    public ReadingService(){
    }

    public double calculateReadingDifference(String meterId, double newReading){
        double initialReading = getInitialReading(meterId);
        double difference = newReading - initialReading;
        return difference;

    }

    private double getInitialReading(String meterId){
        //TODO: get the initial reading from the database
        return 1000;
    }

}
