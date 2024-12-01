package com.ddes.smart_meter_system_back_end.reading;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ReadingRepositoryTest {

    @Autowired
    private ReadingRepository readingRepository;

    @BeforeEach
    public void setUp() {
        Reading reading1 = new Reading();
        reading1.setClientId("client1");
        reading1.setAmount(100.0);
        readingRepository.save(reading1);

        Reading reading2 = new Reading();
        reading2.setClientId("client2");
        reading2.setAmount(200.0);
        readingRepository.save(reading2);

        Reading reading3 = new Reading();
        reading3.setClientId("client1");
        reading3.setAmount(150.0);
        readingRepository.save(reading3);
    }

    @Test
    public void testFindByClientId() {
        List<Reading> readingsClient1 = readingRepository.findByClientId("client1");
        assertThat(readingsClient1).hasSize(2);

        List<Reading> readingsClient2 = readingRepository.findByClientId("client2");
        assertThat(readingsClient2).hasSize(1);

        List<Reading> readingsClient3 = readingRepository.findByClientId("client3");
        assertThat(readingsClient3).isEmpty();
    }
}