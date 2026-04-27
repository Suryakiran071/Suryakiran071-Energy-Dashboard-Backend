package com.energy.dashboard.Repository;

import com.energy.dashboard.Model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends JpaRepository<Meter,Long> {
}
