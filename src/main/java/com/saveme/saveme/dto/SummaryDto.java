package com.saveme.saveme.dto;

public class SummaryDto {
    private long criticalAlerts;
    private long openAlerts;
    private long closedAlerts;
    private long savedPeople;

    public long getCriticalAlerts() {
        return criticalAlerts;
    }

    public void setCriticalAlerts(long criticalAlerts) {
        this.criticalAlerts = criticalAlerts;
    }

    public long getOpenAlerts() {
        return openAlerts;
    }

    public void setOpenAlerts(long openAlerts) {
        this.openAlerts = openAlerts;
    }

    public long getClosedAlerts() {
        return closedAlerts;
    }

    public void setClosedAlerts(long closedAlerts) {
        this.closedAlerts = closedAlerts;
    }

    public long getSavedPeople() {
        return savedPeople;
    }

    public void setSavedPeople(long savedPeople) {
        this.savedPeople = savedPeople;
    }
}
