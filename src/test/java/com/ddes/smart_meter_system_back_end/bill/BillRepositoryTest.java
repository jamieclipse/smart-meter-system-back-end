package com.ddes.smart_meter_system_back_end.bill;

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
public class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @BeforeEach
    public void setUp() {
        Bill bill1 = new Bill();
        bill1.setClientId("client1");
        bill1.setAmount(100.0);
        billRepository.save(bill1);

        Bill bill2 = new Bill();
        bill2.setClientId("client2");
        bill2.setAmount(200.0);
        billRepository.save(bill2);

        Bill bill3 = new Bill();
        bill3.setClientId("client1");
        bill3.setAmount(150.0);
        billRepository.save(bill3);
    }

    @Test
    public void testFindByClientId() {
        List<Bill> billsClient1 = billRepository.findByClientId("client1");
        assertThat(billsClient1).hasSize(2);

        List<Bill> billsClient2 = billRepository.findByClientId("client2");
        assertThat(billsClient2).hasSize(1);

        List<Bill> billsClient3 = billRepository.findByClientId("client3");
        assertThat(billsClient3).isEmpty();
    }
}