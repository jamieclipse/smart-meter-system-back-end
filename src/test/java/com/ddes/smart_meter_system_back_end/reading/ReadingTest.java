package com.ddes.smart_meter_system_back_end.reading;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ReadingTest {

    @Test
    public void testReadingConstructor() {
        Reading reading = new Reading("client1", 100.0);
        assertThat(reading.getClientId()).isEqualTo("client1");
        assertThat(reading.getAmount()).isEqualTo(100.0);
    }

    @Test
    public void testGettersAndSetters() {
        Reading reading = new Reading();
        reading.setClientId("client2");
        reading.setAmount(200.0);

        assertThat(reading.getClientId()).isEqualTo("client2");
        assertThat(reading.getAmount()).isEqualTo(200.0);
    }

    @Test
    public void testToString() {
        Reading reading = new Reading("client1", 100.0);
        reading.setId(1L);
        String expected = "Reading[id=1, clientId='client1', amount='100.0']";
        assertThat(reading.toString()).isEqualTo(expected);
    }
}