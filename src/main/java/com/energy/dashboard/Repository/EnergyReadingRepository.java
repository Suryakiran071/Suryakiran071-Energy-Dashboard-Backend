package com.energy.dashboard.Repository;

import com.energy.dashboard.DTO.LineSummaryDTO;
import com.energy.dashboard.DTO.MeterSummaryDTO;
import com.energy.dashboard.Model.EnergyReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnergyReadingRepository extends JpaRepository<EnergyReading, Long> {

    @Query("SELECT r FROM EnergyReading r WHERE r.meter.line.id = :lineId " +
            "AND r.ts >= :start AND r.ts < :end")
    List<EnergyReading> findByLineAndDateRange(
            @Param("lineId") Long lineId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );

    List<EnergyReading> findByMeterIdAndTsBetween(Long meterId, Instant start, Instant end);

    List<EnergyReading> findByMeterLineIdAndTsBetween(Long lineId, Instant start, Instant end);

    @Query("SELECT r FROM EnergyReading r WHERE r.meter.line.id = :lineId " +
            "AND r.ts >= :start AND r.ts < :end " +
            "ORDER BY r.kwh DESC LIMIT 1")
    Optional<EnergyReading> findTopByLineAndDateRange(
            @Param("lineId") Long lineId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );
    // --- ANALYTICS QUERIES (Add these now) ---

    @Query("SELECT new com.energy.dashboard.DTO.LineSummaryDTO(l.name, SUM(r.kwh), MAX(r.kwh)) " +
            "FROM EnergyReading r JOIN r.meter m JOIN m.line l " +
            "WHERE FUNCTION('DATE', r.ts) = :date " +
            "GROUP BY l.name")
    List<LineSummaryDTO> getOverallLineSummary(@Param("date") LocalDate date);

    @Query("SELECT new com.energy.dashboard.DTO.MeterSummaryDTO(m.name, SUM(r.kwh), MAX(r.kwh)) " +
            "FROM EnergyReading r JOIN r.meter m " +
            "WHERE m.line.id = :lineId AND FUNCTION('DATE', r.ts) = :date " +
            "GROUP BY m.name")
    List<MeterSummaryDTO> getMeterSummaryByLine(@Param("lineId") Long lineId, @Param("date") LocalDate date);
}
