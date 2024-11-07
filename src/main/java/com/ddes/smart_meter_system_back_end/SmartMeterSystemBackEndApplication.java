package com.ddes.smart_meter_system_back_end;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ddes.smart_meter_system_back_end.bill.BillService;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;


@SpringBootApplication
public class SmartMeterSystemBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMeterSystemBackEndApplication.class, args);

	}

}
