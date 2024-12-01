package com.ddes.smart_meter_system_back_end.rabbitmq;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import com.ddes.smart_meter_system_back_end.bill.Bill;
import com.ddes.smart_meter_system_back_end.bill.BillService;
import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

public class MessageConsumerTest {

	@Mock
	private BillService billService;

	@Mock
	private ReadingService readingService;

	@Mock
	private MessageProducer messageProducer;

	@InjectMocks
	private MessageConsumer messageConsumer;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testReceiveMessage() {
		String clientId = "client1";
		double readingValue = 100.0;

		MessageProperties messageProperties = new MessageProperties();
		Map<String, Object> headers = new HashMap<>();
		headers.put("clientId", clientId);
		messageProperties.getHeaders().putAll(headers);

		Message message = new Message(String.valueOf(readingValue).getBytes(), messageProperties);

		Reading reading = new Reading(clientId, readingValue);
		Bill bill = new Bill(clientId, readingValue * 1.5); // Assuming some calculation logic

		when(readingService.saveReading(any(Reading.class))).thenReturn(reading);
		when(billService.calculateBill(any(Reading.class))).thenReturn(bill);

		messageConsumer.receiveMessage(message);

		verify(readingService, times(1)).saveReading(any(Reading.class));
		verify(billService, times(1)).calculateBill(any(Reading.class));
		verify(messageProducer, times(1)).sendMessage(any(Bill.class));
	}

	@Test
	public void testProcessMessage() {
		String clientId = "client1";
		double readingValue = 100.0;

		MessageProperties messageProperties = new MessageProperties();
		Map<String, Object> headers = new HashMap<>();
		headers.put("clientId", clientId);
		messageProperties.getHeaders().putAll(headers);

		Message message = new Message(String.valueOf(readingValue).getBytes(), messageProperties);

		Reading reading = new Reading(clientId, readingValue);
		Bill bill = new Bill(clientId, readingValue * 1.5); // Assuming some calculation logic

		when(readingService.saveReading(any(Reading.class))).thenReturn(reading);
		when(billService.calculateBill(any(Reading.class))).thenReturn(bill);

		messageConsumer.processMessage(message);

		verify(readingService, times(1)).saveReading(any(Reading.class));
		verify(billService, times(1)).calculateBill(any(Reading.class));
		verify(messageProducer, times(1)).sendMessage(any(Bill.class));
	}
}