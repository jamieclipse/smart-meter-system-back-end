package com.ddes.smart_meter_system_back_end.bill;
import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

public class BillService {

    public BillService() {

    }
//pass in reading
//calculate the difference between the current reading and the initial reading
//calculate the bill based on the difference

    public double calculateBill(Reading reading) {
        //instantiate new reader
        ReadingService rs = new ReadingService(); 

        //reading should be passed in from rabbitMQ
        double difference = rs.calculateReadingDifference(reading.getMeterId(), reading.getValue());
        double bill = difference; // TODO: calculate bill based on difference
        return bill;

    }
}
