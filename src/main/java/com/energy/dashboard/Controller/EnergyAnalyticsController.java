package com.energy.dashboard.Controller;

import com.energy.dashboard.DTO.LineSummaryDTO;
import com.energy.dashboard.DTO.MeterSummaryDTO;
import com.energy.dashboard.Model.EnergyReading;
import com.energy.dashboard.Service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class EnergyAnalyticsController {

    @Autowired
    private EnergyService energyService;

    @GetMapping("/dailyTotals")
    public Double getDailyTotals(@RequestParam Long meterId, @RequestParam LocalDate date) {
        return energyService.getDailyTotals(meterId, date);
    }

    @GetMapping("/peaks")
    public EnergyReading getPeakReading(@RequestParam Long lineId, @RequestParam LocalDate date) {
        return energyService.getPeakReadingForLine(lineId, date);
    }

    // Example extension: daily totals per line
    @GetMapping("/lineTotals")
    public Double getLineTotals(@RequestParam Long lineId, @RequestParam LocalDate date) {
        return energyService.getLineTotals(lineId, date);
    }

    // Example extension: peak hour per meter
    @GetMapping("/peakHour")
    public String getPeakHour(@RequestParam Long meterId, @RequestParam LocalDate date) {
        return energyService.getPeakHour(meterId, date);
    }

    // --- NEW: Comparison Endpoints for Charts ---

    /**
     * ADMIN VIEW: Returns a summary of all lines for a bar chart.
     */
    @GetMapping("/comparison/lines")
    public List<LineSummaryDTO> getLineComparison(@RequestParam String date) {
        return energyService.getLineComparison(date);
    }

    /**
     * OPERATOR VIEW: Returns a summary of all meters in a specific line.
     */
    @GetMapping("/comparison/meters")
    public List<MeterSummaryDTO> getMeterComparison(@RequestParam Long lineId, @RequestParam String date) {
        return energyService.getMeterComparison(lineId, date);
    }
}
