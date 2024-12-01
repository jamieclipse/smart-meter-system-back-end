package com.ddes.smart_meter_system_back_end.bill;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BillTest {

    @Test
    public void testBillConstructor() {
        Bill bill = new Bill("client1", 100.0);
        assertThat(bill.getClientId()).isEqualTo("client1");
        assertThat(bill.getAmount()).isEqualTo(100.0);
    }

    @Test
    public void testGettersAndSetters() {
        Bill bill = new Bill();
        bill.setClientId("client2");
        bill.setAmount(200.0);

        assertThat(bill.getClientId()).isEqualTo("client2");
        assertThat(bill.getAmount()).isEqualTo(200.0);
    }

    @Test
    public void testToString() {
        Bill bill = new Bill("client1", 100.0);
        bill.setId(1L);
        String expected = "Bill[id=1, clientId='client1', amount='100.0']";
        assertThat(bill.toString()).isEqualTo(expected);
    }
}