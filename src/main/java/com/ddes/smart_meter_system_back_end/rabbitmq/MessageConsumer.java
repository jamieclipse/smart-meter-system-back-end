package com.ddes.smart_meter_system_back_end.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = "queue")
    public void receiveMessage(String message){
        System.out.println(message);
    }

}
