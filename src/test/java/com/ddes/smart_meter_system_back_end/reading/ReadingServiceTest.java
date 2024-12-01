package com.ddes.smart_meter_system_back_end.reading;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReadingServiceTest {
    
    @Mock
    private ReadingRepository readingRepository;

    @InjectMocks
    private ReadingService readingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateReadingDifference() {
        Reading initialReading = new Reading("client1", 100.0);
        when(readingRepository.findByClientId("client1")).thenReturn(Arrays.asList(initialReading));

        double difference = readingService.calculateReadingDifference("client1", 200.0);

        assertThat(difference).isEqualTo(100.0);
    }

    @Test
    public void testGetInitialReading() {
        Reading initialReading = new Reading("client1", 100.0);
        when(readingRepository.findByClientId("client1")).thenReturn(Arrays.asList(initialReading));

        double initial = readingService.calculateReadingDifference("client1", 200.0);

        assertThat(initial).isEqualTo(100.0);
    }

    @Test
    public void testGetAllReadings() {
        Reading reading1 = new Reading("client1", 100.0);
        Reading reading2 = new Reading("client2", 200.0);
        when(readingRepository.findAll()).thenReturn(Arrays.asList(reading1, reading2));

        List<Reading> readings = readingService.getAllReadings();

        assertThat(readings).hasSize(2);
        assertThat(readings).contains(reading1, reading2);
    }

    @Test
    public void testSaveReading() {
        Reading reading = new Reading("client1", 100.0);
        when(readingRepository.save(any(Reading.class))).thenReturn(reading);

        Reading savedReading = readingService.saveReading(reading);

        assertThat(savedReading).isEqualTo(reading);
        verify(readingRepository, times(1)).save(reading);
    }
}
