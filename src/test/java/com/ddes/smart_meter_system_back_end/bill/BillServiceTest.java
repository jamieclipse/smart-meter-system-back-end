package com.ddes.smart_meter_system_back_end.bill;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ddes.smart_meter_system_back_end.reading.Reading;
import com.ddes.smart_meter_system_back_end.reading.ReadingService;

public class BillServiceTest {
    
    @Mock
    private ReadingService readingService;

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        billService.tariff = 0.15;
        billService.standingCharge = 5.0;
        billService.vat = 1.2;
    }

    @Test
    public void testCalculateBill() {
        Reading reading = new Reading("client1", 200.0);
        when(readingService.calculateReadingDifference(anyString(), anyDouble())).thenReturn(100.0);

        Bill bill = billService.calculateBill(reading);

        assertThat(bill.getClientId()).isEqualTo("client1");
        assertThat(bill.getAmount()).isEqualTo(24.0); // ((100 * 0.15) + 5) * 1.2
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    public void testSaveBill() {
        Bill bill = new Bill("client1", 100.0);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        Bill savedBill = billService.saveBill(bill);

        assertThat(savedBill).isEqualTo(bill);
        verify(billRepository, times(1)).save(bill);
    }
}
