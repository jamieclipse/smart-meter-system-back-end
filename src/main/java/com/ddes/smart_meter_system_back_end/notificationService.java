package com.ddes.smart_meter_system_back_end.notification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ddes.smart_meter_system_back_end.rabbitmq.MessageProducer;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class NotificationService {

    @Autowired
    private MessageProducer messageProducer;

    @Scheduled(fixedRate = 30000)
    public void sendNotification() {
        messageProducer.sendNotification("Electricity power grid is having problems.");
    }
}
