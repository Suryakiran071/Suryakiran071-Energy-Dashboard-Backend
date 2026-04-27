package com.energy.dashboard.Repository;

import com.energy.dashboard.Model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line,Long> {
}
