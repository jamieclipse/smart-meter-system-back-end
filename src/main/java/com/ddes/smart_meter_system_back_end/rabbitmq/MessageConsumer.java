package com.ddes.smart_meter_system_back_end.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    int reading;

    @RabbitListener(queues = "server.inbound")
    public void receiveMessage(Message message){
        System.out.println("Message received with following properties: " + message.getMessageProperties());
        System.out.println("Message received with following payload: " + new String(message.getBody()));
        this.reading = Integer.parseInt(message.getBody().toString());
        // TODO: Convert this to proper logging
    }

    public int returnReading(){
        return this.reading;
    }

}
