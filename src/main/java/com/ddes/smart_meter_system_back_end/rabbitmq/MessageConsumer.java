package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddes.smart_meter_system_back_end.bill.Bill;
import com.ddes.smart_meter_system_back_end.bill.BillService;
import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;
import com.ddes.smart_meter_system_back_end.json.JsonService;

@Component
public class MessageConsumer {
    Logger log = Logger.getLogger(MessageConsumer.class.getName());

    @Autowired
	private BillService billService;

	@Autowired
	private ReadingService ReadingService;

	@Autowired
	private MessageProducer messageProducer;
	
	@RabbitListener(queues = "server.readings")
    public void receiveMessage(Message message) {
        log.info("Message received with following properties: " + message.getMessageProperties().toString());
        log.info("Message received with following payload: " +  new String(message.getBody()));
		processMessage(message);
	}

	void processMessage(Message message) {
		Map<String,Object> headers = message.getMessageProperties().getHeaders();

		String clientId = (String) headers.get("clientId");
		log.info("Successfully extracted Client ID: " + clientId);

		//convert json reading into reading object using JsonService
        	Reading reading = JsonService.fromJsonToReading(new String(message.getBody()));
        	log.info("Successfully extracted reading value: " + reading.getAmount());

		//previous code
		//double readingValue = Double.parseDouble(new String(message.getBody()));
		//log.info("Successfully extracted reading value: " + readingValue);

		Reading reading = new Reading(clientId, readingValue);
		log.info("Successfully created reading object with ID: " + reading.getId());

		// Store the reading in the database
		ReadingService.saveReading(reading);

		Bill bill = billService.calculateBill(reading);

		messageProducer.sendMessage(bill);
	}
}
