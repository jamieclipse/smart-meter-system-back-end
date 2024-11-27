package com.ddes.smart_meter_system_back_end.bill;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
@PropertySource("classpath:config.properties")
public class BillService {
    Logger log = Logger.getLogger(BillService.class.getName());

    @Autowired
    private ReadingService readingService;

    @Autowired
    private BillRepository billRepository;
    
    @Value("${tariff}")
    private double tariff;
    @Value("${standingCharge}")
    private double standingCharge;
    @Value("${vat}")
    private double vat;

    public Bill calculateBill(Reading reading) {
        double difference = readingService.calculateReadingDifference(reading.getClientId(), reading.getAmount());
        log.info("Successfully calculated difference between current and initial reading: " + difference);
        double result = ((difference * tariff) + standingCharge) * vat;
        Bill bill = new Bill(reading.getClientId(), result);
        return bill;
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }
}
