package com.energy.dashboard.Service;

import com.energy.dashboard.Model.Line;
import com.energy.dashboard.Repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService {

    @Autowired
    LineRepository lineRepository;

    public List<Line> getAllLines() {
        return lineRepository.findAll();
    }

    public Line getLineById(Long id) {
        return lineRepository.findById(id).orElse(null);
    }

    public void createLine(Line line) {
        lineRepository.save(line);
    }

    public Line updateLine(Long id, Line newLine) {
        Line line=lineRepository.findById(id).orElse(null);
        if (line != null) {
            line.setName(newLine.getName());
            return lineRepository.save(line);
        }
        return null;
    }

    public void deleteLine(Long id) {
        lineRepository.deleteById(id);
    }
}
