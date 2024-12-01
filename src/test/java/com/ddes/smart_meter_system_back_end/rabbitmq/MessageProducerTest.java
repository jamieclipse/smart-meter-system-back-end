package com.ddes.smart_meter_system_back_end.rabbitmq;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import com.ddes.smart_meter_system_back_end.bill.Bill;

@SpringBootTest
public class MessageProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private DirectExchange billsExchange;

    @Mock
    private FanoutExchange notificationExchange;

    @InjectMocks
    private MessageProducer messageProducer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(billsExchange.getName()).thenReturn("server.bills");
        when(notificationExchange.getName()).thenReturn("notifications");
    }

    @Test
    public void testSendMessage() {
        Bill bill = new Bill("client1", 100.0);
        messageProducer.sendMessage(bill);

        verify(rabbitTemplate, times(1)).send(eq("server.bills"), eq("client1"), any(Message.class));
    }

    @Test
    public void testSendNotification() {
        String notification = "grid_down";
        messageProducer.sendNotification(notification);

        verify(rabbitTemplate, times(1)).send(eq("notifications"), eq("notifications"), any(Message.class));
    }
}