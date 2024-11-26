package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddes.smart_meter_system_back_end.bill.Bill;

@Component
public class MessageProducer {
    Logger log = Logger.getLogger(MessageProducer.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    public void sendMessage(Bill bill) {
        try {
            // Create the message properties
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("clientId", bill.getClientId());
            
            // Create the message
            Message message = new SimpleMessageConverter().toMessage(Double.toString(bill.getBillAmount()), messageProperties);

            // Use the clientId as the routing key
            rabbitTemplate.send(directExchange.getName(), bill.getClientId(), message);
            log.info("Message sent to the exchange with routing key: " + bill.getClientId());
        } 
        
        catch (Exception e) {
            log.severe("Error sending message: " + e.toString());
        }
        
    }
        public void sendNotification(String notification) {
        try {
            MessageProperties messageProperties = new MessageProperties();
            Message message = new SimpleMessageConverter().toMessage(notification, messageProperties);
            rabbitTemplate.send(directExchange.getName(), "notifications", message);
            log.info("Notification sent to the exchange.");
        } catch (Exception e) {
            log.severe("Error sending notification: " + e.toString());
        }
    }
}
