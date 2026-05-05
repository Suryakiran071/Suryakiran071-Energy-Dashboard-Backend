package com.energy.dashboard.Controller;

import com.energy.dashboard.Model.Line;
import com.energy.dashboard.Service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Add this!
@RestController
@RequestMapping("/api/lines")
public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping()
    public List<Line> getAllLines() {
        return lineService.getAllLines();
    }

    @GetMapping("/{id}")
    public Line getLinesById(@PathVariable Long id) {
        return lineService.getLineById(id);
    }

    @PostMapping
    public void createLine(@RequestBody Line line){
        lineService.createLine(line);
    }

    @PutMapping("/{id}")
    public void updateLine(@PathVariable Long id,@RequestBody Line line){
        lineService.updateLine(id,line);
    }

    @DeleteMapping("/{id}")
    public void deleteLine(@PathVariable Long id) {
        lineService.deleteLine(id);
    }
}
