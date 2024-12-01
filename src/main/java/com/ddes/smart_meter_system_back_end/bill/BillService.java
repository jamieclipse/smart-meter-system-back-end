package com.ddes.smart_meter_system_back_end.bill;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

@Service
@PropertySource("classpath:billing.properties")
public class BillService {
    Logger log = Logger.getLogger(BillService.class.getName());

    @Autowired
    private ReadingService readingService;

    @Autowired
    private BillRepository billRepository;
    
    @Value("${tariff}") double tariff;
    @Value("${standingCharge}") double standingCharge;
    @Value("${vat}") double vat;

    public Bill calculateBill(Reading reading) {
        double difference = readingService.calculateReadingDifference(reading.getClientId(), reading.getAmount());
        log.info("Successfully calculated difference between current and initial reading: " + difference);
        double result = ((difference * tariff) + standingCharge) * vat;
        Bill bill = new Bill(reading.getClientId(), result);
        log.info("Successfully calculated bill amount: " + bill.getAmount());
        saveBill(bill);
        log.info("Successfully saved bill to database.");
        return bill;
    }

    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }
}
