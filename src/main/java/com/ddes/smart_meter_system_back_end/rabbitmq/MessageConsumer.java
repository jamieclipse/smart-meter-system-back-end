package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    Logger log = Logger.getLogger(MessageConsumer.class.getName());

    @RabbitListener(queues = "server.inbound")
    public void receiveMessage(Message message){
        log.info("Message received with following properties: " + message.getMessageProperties());
        log.info("Message received with following payload: " + new String(message.getBody()));    
    }
	
	//mc.receiveMessage();
	//instantiate readingService
	//instantiate BillService
	//readingService.calculateDifference()
	//pass output to Bill Service
	//BillService.calculateBill()
	//pass to MessageProducer to sent back to client

}
