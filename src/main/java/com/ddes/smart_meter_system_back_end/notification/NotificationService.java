package com.ddes.smart_meter_system_back_end.notification;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.ddes.smart_meter_system_back_end.rabbitmq.MessageProducer;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class NotificationService {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;
    
    private final Random random = new Random();

    @PostConstruct
    public void startNotificationService() {
        scheduleNextNotification(true);
    }

    @PreDestroy
    public void shutdownScheduler() {
        taskScheduler.shutdown();
    }

    private void scheduleNextNotification(boolean down) {
        int delay = random.nextInt(300000,600000); // Random delay between 0 and 60 seconds
        switch (down ? 1 : 0) {
            case 1:
            taskScheduler.schedule(this::sendDownNotification, triggerContext -> new Date(System.currentTimeMillis() + delay).toInstant());
            break;
            case 0:
            taskScheduler.schedule(this::sendUpNotification, triggerContext -> new Date(System.currentTimeMillis() + delay).toInstant());
            break;
        }
    }

    private void sendDownNotification() {
        messageProducer.sendNotification("grid_down");
        scheduleNextNotification(false); // Ensure next notification is an up notification
    }

    private void sendUpNotification() {
        messageProducer.sendNotification("grid_up");
        scheduleNextNotification(true); // Ensure next notification is a down notification
    }
}
