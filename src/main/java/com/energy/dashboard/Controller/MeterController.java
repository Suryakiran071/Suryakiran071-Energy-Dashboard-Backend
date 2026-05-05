package com.energy.dashboard.Controller;

import com.energy.dashboard.Model.Meter;
import com.energy.dashboard.Service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meters")
public class MeterController {

    @Autowired
    private MeterService meterService;

    @GetMapping
    public List<Meter> getAllMeters() {
        return meterService.getAllMeters();
    }

    @GetMapping("/{id}")
    public Meter getMeterById(@PathVariable Long id) {
        return meterService.getMeterById(id);
    }

    @PostMapping
    public Meter createMeter(@RequestBody Meter meter) {
        return meterService.createMeter(meter);
    }

    @PutMapping("/{id}")
    public Meter updateMeter(@PathVariable Long id, @RequestBody Meter meterDetails) {
        return meterService.updateMeter(id, meterDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMeter(@PathVariable Long id) {
        meterService.deleteMeter(id);
    }
}
