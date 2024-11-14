package com.ddes.smart_meter_system_back_end.bill;
import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

public class BillService {

    public BillService() {

    }

    public double calculateBill(Reading reading) {
        ReadingService rs = new ReadingService(); 

        double difference = rs.calculateReadingDifference(reading.getMeterId(), reading.getValue());
        double tariff = 0.21; //TODO: get the tariff from somewhere else
        double standingCharge = 0.60; //TODO: get the standing charge from somewhere else
        double VAT = 1.05; //TODO: get the VAT from somewhere else
        double bill = ((difference * tariff) + standingCharge) * VAT;
        return bill;
    }
}
