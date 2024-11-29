package com.ddes.smart_meter_system_back_end.notification;

import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.ddes.smart_meter_system_back_end.rabbitmq.MessageProducer;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class NotificationService {
    Logger log = Logger.getLogger(NotificationService.class.getName());

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;
    
    private final Random random = new Random();

    @PostConstruct
    public void startNotificationService() {
        log.info("Starting Grid Health Notification Service.");
        scheduleNextNotification(true);
    }

    @PreDestroy
    public void shutdownScheduler() {
        taskScheduler.shutdown();
    }

    private void scheduleNextNotification(boolean down) {
        int delay = random.nextInt(120000,180000); // Random delay between 2 and 3 minutes
        log.info("Next grid health notification in " + (delay / 1000) + " seconds.");
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