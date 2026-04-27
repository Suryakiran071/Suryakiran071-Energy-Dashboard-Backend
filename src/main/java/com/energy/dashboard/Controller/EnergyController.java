package com.energy.dashboard.Controller;

import com.energy.dashboard.Model.EnergyReading;
import com.energy.dashboard.Service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class EnergyController {

    @Autowired
    private EnergyService energyService;

    @GetMapping
    public List<EnergyReading> getAllReadings() {
        return energyService.getAllReadings();
    }

    @GetMapping("/{id}")
    public EnergyReading getReadingById(@PathVariable Long id) {
        return energyService.getReadingById(id);
    }

    @PostMapping
    public EnergyReading createReading(@RequestBody EnergyReading reading) {
        return energyService.createReading(reading);
    }

    @PutMapping("/{id}")
    public EnergyReading updateReading(@PathVariable Long id, @RequestBody EnergyReading readingDetails) {
        return energyService.updateReading(id, readingDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteReading(@PathVariable Long id) {
        energyService.deleteReading(id);
    }
}
