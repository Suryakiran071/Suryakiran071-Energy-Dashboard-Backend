package com.energy.dashboard.Service;

import com.energy.dashboard.DTO.LineSummaryDTO;
import com.energy.dashboard.DTO.MeterSummaryDTO;
import com.energy.dashboard.Model.EnergyReading;
import com.energy.dashboard.Repository.EnergyReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class EnergyService {

    @Autowired
    private EnergyReadingRepository energyReadingRepository;

    // --- CRUD Wrappers ---
    public List<EnergyReading> getAllReadings() {
        return energyReadingRepository.findAll();
    }

    public List<EnergyReading> getReadingsByLineAndDate(Long lineId, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        Instant start = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = date.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        return energyReadingRepository.findByLineAndDateRange(lineId, start, end);
    }

    public EnergyReading getReadingById(Long id) {
        return energyReadingRepository.findById(id).orElse(null);
    }

    public EnergyReading createReading(EnergyReading reading) {
        return energyReadingRepository.save(reading);
    }

    public EnergyReading updateReading(Long id, EnergyReading readingDetails) {
        EnergyReading reading = energyReadingRepository.findById(id).orElse(null);
        if (reading != null) {
            reading.setKwh(readingDetails.getKwh());
            reading.setTs(readingDetails.getTs());
            reading.setMeter(readingDetails.getMeter());
            return energyReadingRepository.save(reading);
        }
        return null;
    }

    public void deleteReading(Long id) {
        energyReadingRepository.deleteById(id);
    }

    // --- Analytics Methods ---
    public Double getDailyTotals(Long meterId, LocalDate date) {
        Instant start = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = date.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        List<EnergyReading> readings = energyReadingRepository.findByMeterIdAndTsBetween(meterId, start, end);

        double total = 0.0;
        for (EnergyReading r : readings) {
            total += r.getKwh();
        }
        return total;
    }

    public EnergyReading getPeakReadingForLine(Long lineId, LocalDate date) {
        Instant start = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = date.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        return energyReadingRepository.findTopByLineAndDateRange(lineId, start, end)
                .orElse(null);
    }

    public Double getLineTotals(Long lineId, LocalDate date) {
        Instant start = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = date.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        List<EnergyReading> readings = energyReadingRepository.findByMeterLineIdAndTsBetween(lineId, start, end);

        double total = 0.0;
        for (EnergyReading r : readings) {
            total += r.getKwh();
        }
        return total;
    }

    public String getPeakHour(Long meterId, LocalDate date) {
        Instant start = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = date.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        List<EnergyReading> readings = energyReadingRepository.findByMeterIdAndTsBetween(meterId, start, end);

        EnergyReading peak = null;
        for (EnergyReading r : readings) {
            if (peak == null || r.getKwh() > peak.getKwh()) {
                peak = r;
            }
        }
        return (peak != null) ? peak.getTs().atZone(ZoneId.systemDefault()).toLocalTime().toString() : null;
    }

    public List<LineSummaryDTO> getLineComparison(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return energyReadingRepository.getOverallLineSummary(date);
    }

    public List<MeterSummaryDTO> getMeterComparison(Long lineId, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return energyReadingRepository.getMeterSummaryByLine(lineId, date);
    }
}
