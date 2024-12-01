package com.ddes.smart_meter_system_back_end.notification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.ScheduledFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.ddes.smart_meter_system_back_end.rabbitmq.MessageProducer;

@SpringBootTest
public class NotificationServiceTest {

    @Mock
    private MessageProducer messageProducer;

    @Mock
    private ThreadPoolTaskScheduler taskScheduler;

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private ScheduledFuture<?> scheduledFuture;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.doReturn(scheduledFuture).when(taskScheduler).schedule(any(Runnable.class), any(org.springframework.scheduling.Trigger.class));
    }

    @Test
    public void testStartNotificationService() {
        notificationService.startNotificationService();
        verify(taskScheduler, times(1)).schedule(any(Runnable.class), any(org.springframework.scheduling.Trigger.class));
    }

    @Test
    public void testShutdownScheduler() {
        notificationService.startNotificationService();
        notificationService.shutdownScheduler();
        verify(scheduledFuture, times(2)).cancel(true);
        verify(taskScheduler, times(1)).shutdown();
    }

    @Test
    public void testSendDownNotification() {
        notificationService.sendDownNotification();
        verify(messageProducer, times(1)).sendNotification("grid_down");
        verify(taskScheduler, times(1)).schedule(any(Runnable.class), any(org.springframework.scheduling.Trigger.class));
    }

    @Test
    public void testSendUpNotification() {
        notificationService.sendUpNotification();
        verify(messageProducer, times(1)).sendNotification("grid_up");
        verify(taskScheduler, times(1)).schedule(any(Runnable.class), any(org.springframework.scheduling.Trigger.class));
    }
}