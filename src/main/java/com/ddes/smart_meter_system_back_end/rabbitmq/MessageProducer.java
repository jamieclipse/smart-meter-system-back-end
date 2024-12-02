package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddes.smart_meter_system_back_end.bill.Bill;
import com.ddes.smart_meter_system_back_end.json.JsonService;

@Component
public class MessageProducer {
    Logger log = Logger.getLogger(MessageProducer.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange billsExchange;

    @Autowired
    private FanoutExchange notificationExchange;

    public void sendMessage(Bill bill) {
        try {
            // Create the message properties
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("clientId", bill.getClientId());

            //convert the bill to json using JsonService
            String jsonBill = JsonService.toJson(bill);
            
            // Create the message
            Message message = new SimpleMessageConverter().toMessage(jsonBill, messageProperties);

            // Use the clientId as the routing key
            rabbitTemplate.send(billsExchange.getName(), bill.getClientId(), message);
            log.info("Message sent to the exchange with routing key: " + bill.getClientId());
        } 
        catch (Exception e) {
            log.severe("Error sending message: " + e.toString());
        }
        
    }
        public void sendNotification(String notification) {
        try {
            MessageProperties messageProperties = new MessageProperties();

            //convert notification to json
            String jsonNotification = JsonService.createJson("notification: ", notification);
            
            Message message = new SimpleMessageConverter().toMessage(jsonNotification, messageProperties);
            
            rabbitTemplate.send(notificationExchange.getName(), "notifications", message);
            log.info("Notification sent to the exchange: " + notification);
        } 
        catch (Exception e) {
            log.severe("Error sending notification: " + e.toString());
        }
    }
}
