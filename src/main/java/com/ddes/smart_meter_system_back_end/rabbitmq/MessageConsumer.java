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

@Component
public class MessageConsumer {
    Logger log = Logger.getLogger(MessageConsumer.class.getName());

    @Autowired
	private BillService billService;

	@Autowired
	private MessageProducer messageProducer;
	
	@RabbitListener(queues = "server.readings")
    public void receiveMessage(Message message) {
        log.info("Message received with following properties: " + message.getMessageProperties().toString());
        log.info("Message received with following payload: " +  new String(message.getBody()));
		processMessage(message);
	}

	private void processMessage(Message message) {
		Map<String,Object> headers = message.getMessageProperties().getHeaders();

		String clientId = (String) headers.get("clientId");
		log.info("Successfully extracted Meter ID: " + clientId);

		double value = Double.parseDouble(new String(message.getBody()));
		log.info("Successfully extracted reading value: " + value);

		Reading reading = new Reading(clientId, value);
		log.info("Successfully created reading object with ID: " + reading.getId());

		Bill bill = billService.calculateBill(reading);
		log.info("Successfully calculated bill amount: " + bill.getBillAmount());

		messageProducer.sendMessage(bill);
	}
}
