package com.saveme.saveme.dto;

public class SummaryDto {
    private long tapped;
    private long missing;
    private long criticalAlerts;

    public long getTapped() {
        return tapped;
    }

    public void setTapped(long tapped) {
        this.tapped = tapped;
    }

    public long getMissing() {
        return missing;
    }

    public void setMissing(long missing) {
        this.missing = missing;
    }

    public long getCriticalAlerts() {
        return criticalAlerts;
    }

    public void setCriticalAlerts(long criticalAlerts) {
        this.criticalAlerts = criticalAlerts;
    }
}
