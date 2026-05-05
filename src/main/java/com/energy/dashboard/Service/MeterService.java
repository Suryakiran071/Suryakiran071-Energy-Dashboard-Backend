package com.energy.dashboard.Service;

import com.energy.dashboard.Model.Meter;
import com.energy.dashboard.Repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterService {

    @Autowired
    private MeterRepository meterRepo;

    public List<Meter> getAllMeters() {
        return meterRepo.findAll();
    }

    public Meter getMeterById(Long id) {
        return meterRepo.findById(id).orElse(null);
    }

    public Meter createMeter(Meter meter) {
        return meterRepo.save(meter);
    }

    public Meter updateMeter(Long id, Meter meterDetails) {
        Meter meter = meterRepo.findById(id).orElse(null);
        if (meter != null) {
            meter.setName(meterDetails.getName());
            meter.setLine(meterDetails.getLine());
            return meterRepo.save(meter);
        }
        return null;
    }

    public void deleteMeter(Long id) {
        meterRepo.deleteById(id);
    }
}
