package com.ddes.smart_meter_system_back_end.bill;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {
    List<Bill> findByMeterId(String meterId);
}
