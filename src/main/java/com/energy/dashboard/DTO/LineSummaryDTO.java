package com.energy.dashboard.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LineSummaryDTO {
    private String name;
    private Double totalKwh;
    private Double peakKwh;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalKwh() {
        return totalKwh;
    }

    public void setTotalKwh(Double totalKwh) {
        this.totalKwh = totalKwh;
    }

    public Double getPeakKwh() {
        return peakKwh;
    }

    public void setPeakKwh(Double peakKwh) {
        this.peakKwh = peakKwh;
    }
}