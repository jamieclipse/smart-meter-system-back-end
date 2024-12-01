package com.ddes.smart_meter_system_back_end.reading;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {
    Logger log = Logger.getLogger(ReadingService.class.getName());

    @Autowired
    private ReadingRepository readingRepository;
    
    public double calculateReadingDifference(String clientId, double newReading){
        double initialReading = getInitialReading(clientId);
        log.info("Successfully extracted initial reading: " + initialReading);
        double difference = newReading - initialReading; // New reading should always be greater than initial reading
        return difference;

    }

    private double getInitialReading(String clientId){
        List<Reading> readings = readingRepository.findByClientId(clientId);
        if (readings.isEmpty()) { // This should never happen, but a null pointer exception is worse
            return 0;
        }
        return readings.get(0).getAmount();
    }

    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    public Reading saveReading(Reading reading) {
        return readingRepository.save(reading);
    }

}
