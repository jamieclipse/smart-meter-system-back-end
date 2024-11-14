package com.ddes.smart_meter_system_back_end.bill;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

import org.springframework.beans.factory.annotation.Value;

@Component
@PropertySource("classpath:config.properties")
public class BillService {

    @Value("${tariff}")
    private double tariff;
    @Value("${standingCharge}")
    private double standingCharge;
    @Value("${vat}")
    private double vat;
    
    public BillService() {

    }

    public double calculateBill(Reading reading) {
        ReadingService rs = new ReadingService(); 

        double difference = rs.calculateReadingDifference(reading.getMeterId(), reading.getValue());
        double bill = ((difference * tariff) + standingCharge) * vat;
        return bill;
    }
}
