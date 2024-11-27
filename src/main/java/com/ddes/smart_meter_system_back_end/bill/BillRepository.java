package com.ddes.smart_meter_system_back_end.bill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByClientId(String clientId);
}
