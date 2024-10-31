package com.ddes.smart_meter_system_back_end.bill;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

public class BillService {

    public BillService() {

    }
//pass in electricity used from Bill Service
    public double calculateBill(double electricityUsed) {
        //instantiate new reader
        ReadingService reader = new ReadingService(); 

        //reading should be passed in from rabbitMQ
        reader.calculateReadingDifference(0); 
        double bill = electricityUsed * 2;
        return 2.2;
    }
}
