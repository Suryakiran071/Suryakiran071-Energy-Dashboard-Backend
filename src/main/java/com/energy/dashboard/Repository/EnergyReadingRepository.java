package com.energy.dashboard.Repository;

import com.energy.dashboard.Model.EnergyReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnergyReadingRepository extends JpaRepository<EnergyReading,Long> {
    List<EnergyReading> findByMeterIdAndTsBetween(Long meterId, LocalDateTime start, LocalDateTime end);

    List<EnergyReading> findByMeterLineIdAndTsBetween(Long lineId, LocalDateTime start, LocalDateTime end);
}
