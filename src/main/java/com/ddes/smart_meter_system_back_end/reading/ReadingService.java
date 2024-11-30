package com.ddes.smart_meter_system_back_end.reading;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;
    
    public double calculateReadingDifference(String clientId, double newReading){
        double initialReading = getInitialReading(clientId);
        double difference = newReading - initialReading; // New reading should always be greater than initial reading
        return difference;

    }

    private double getInitialReading(String clientId){
        List<Reading> readings = readingRepository.findByClientId(clientId);
        return readings.get(0).getAmount();
    }

    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    public Reading saveReading(Reading reading) {
        return readingRepository.save(reading);
    }

}
