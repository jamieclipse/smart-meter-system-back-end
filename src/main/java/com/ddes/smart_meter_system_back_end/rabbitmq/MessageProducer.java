package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageProducer {
    Logger log = Logger.getLogger(MessageConsumer.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String meterId, double billAmount){
        //rabbitTemplate.convertAndSend("server.outbound", "Meter ID: " + meterId + " Bill Amount: " + billAmount);
        log.info("Message is ready to send.");
    }
}
