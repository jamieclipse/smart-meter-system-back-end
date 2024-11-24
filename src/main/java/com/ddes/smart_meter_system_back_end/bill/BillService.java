package com.ddes.smart_meter_system_back_end.bill;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

@Component
@PropertySource("classpath:config.properties")
public class BillService {
    Logger log = Logger.getLogger(BillService.class.getName());

    @Value("${tariff}")
    private double tariff;
    @Value("${standingCharge}")
    private double standingCharge;
    @Value("${vat}")
    private double vat;
    
    public BillService() {

    }

    public double calculateBill(Reading reading) {
        ReadingService readingService = new ReadingService(); 

        double difference = readingService.calculateReadingDifference(reading.getClientId(), reading.getValue());
        log.info("Successfully calculated difference between current and initial reading: " + difference);
        double bill = ((difference * tariff) + standingCharge) * vat;
        return bill;
    }
}
