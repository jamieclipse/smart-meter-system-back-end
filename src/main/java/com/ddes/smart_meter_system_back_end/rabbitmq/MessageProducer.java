package com.ddes.smart_meter_system_back_end.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String meterId, double billAmount){
        rabbitTemplate.convertAndSend("server.outbound", "Meter ID: " + meterId + " Bill Amount: " + billAmount);
    }
}
