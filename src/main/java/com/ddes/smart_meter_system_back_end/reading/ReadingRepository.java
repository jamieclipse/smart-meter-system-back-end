package com.ddes.smart_meter_system_back_end.reading;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findByClientId(String clientId);
    
}
