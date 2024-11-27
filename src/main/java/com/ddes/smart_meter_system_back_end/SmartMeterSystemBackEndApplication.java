package com.ddes.smart_meter_system_back_end;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SmartMeterSystemBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMeterSystemBackEndApplication.class, args);

	}

}
