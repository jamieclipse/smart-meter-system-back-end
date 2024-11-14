package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ddes.smart_meter_system_back_end.bill.BillService;
import com.ddes.smart_meter_system_back_end.reading.Reading;

@Component
public class MessageConsumer {
    Logger log = Logger.getLogger(MessageConsumer.class.getName());

    @RabbitListener(queues = "server.inbound")
    public void receiveMessage(Message message) {
        log.info("Message received with following properties: " + message.getMessageProperties().toString());
        log.info("Message received with following payload: " +  new String(message.getBody()));
		processMessage(message);
	}

	private void processMessage(Message message) {
		Map<String,Object> headers = message.getMessageProperties().getHeaders();

		String meterId = (String) headers.get("meterId");
		log.info("Successfully extracted Meter ID: " + meterId);

		double value = Double.parseDouble(new String(message.getBody()));
		log.info("Successfully extracted reading value: " + value);

		Reading reading = new Reading(meterId, value);
		log.info("Successfully created reading object with ID: " + reading.getId());

		BillService billService = new BillService();
		double billAmount = billService.calculateBill(reading);
		log.info("Successfully calculated bill amount: " + billAmount);

		MessageProducer messageProducer = new MessageProducer();
		messageProducer.sendMessage(meterId, billAmount);
	}
}
