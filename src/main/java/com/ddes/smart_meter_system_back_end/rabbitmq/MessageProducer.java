package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MessageProducer {
    Logger log = Logger.getLogger(MessageProducer.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private DirectExchange directExchange;

    @PostConstruct
    public void init() { // For debugging purposes only, remove later
        log.info("RabbitTemplate: " + rabbitTemplate);
        log.info("RabbitAdmin: " + rabbitAdmin);
        log.info("DirectExchange: " + directExchange);
    }

    public void sendMessage(String clientId, double billAmount) {
        try {
            // Ensure the client queue exists
            declareClientQueue(clientId);

            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("clientId", clientId);
            Message message = new SimpleMessageConverter().toMessage(billAmount, messageProperties);

            // Use the clientId as the routing key
            rabbitTemplate.send(directExchange.getName(), clientId, message);
            log.info("Message sent to the exchange with routing key: " + clientId);
        } 
        
        catch (Exception e) {
            log.severe("Error sending message: " + e.getMessage());
        }
        
    }
    
    private void declareClientQueue(String clientId) {
        Queue queue = new Queue(clientId + ".bills");
        rabbitAdmin.declareQueue(queue);
    }
    
}
