package com.energy.dashboard.Controller;

import com.energy.dashboard.Model.EnergyReading;
import com.energy.dashboard.Service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public EnergyReading getPeakReading(@RequestParam Long meterId, @RequestParam LocalDate date) {
        return energyService.getPeakReading(meterId, date);
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
}
