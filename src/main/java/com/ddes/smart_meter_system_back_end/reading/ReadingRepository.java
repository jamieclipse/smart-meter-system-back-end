package com.ddes.smart_meter_system_back_end.reading;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReadingRepository extends CrudRepository<Reading, Long> {
    List<Reading> findByMeterId(String meterId);
    
}
