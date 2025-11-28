package com.saveme.saveme.dto;

import java.util.List;

public class NotificationDto {
    private String reporterName;
    private List<String> telephoneNumbers;
    private Double latitude;
    private Double longitude;
    private String severity;
    private boolean isRoadBlocked;
    private String location;
    private List<String> photos;
    private int affectedPeople;
    private int affectedChildren;
    private int affectedYoung;
    private int affectedAdults;
    private String comments;
    private Double waterLevel;
    private String type; // "flood", "landslide", "other"

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public List<String> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(List<String> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean isRoadBlocked() {
        return isRoadBlocked;
    }

    public void setRoadBlocked(boolean roadBlocked) {
        isRoadBlocked = roadBlocked;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public int getAffectedPeople() {
        return affectedPeople;
    }

    public void setAffectedPeople(int affectedPeople) {
        this.affectedPeople = affectedPeople;
    }

    public int getAffectedChildren() {
        return affectedChildren;
    }

    public void setAffectedChildren(int affectedChildren) {
        this.affectedChildren = affectedChildren;
    }

    public int getAffectedYoung() {
        return affectedYoung;
    }

    public void setAffectedYoung(int affectedYoung) {
        this.affectedYoung = affectedYoung;
    }

    public int getAffectedAdults() {
        return affectedAdults;
    }

    public void setAffectedAdults(int affectedAdults) {
        this.affectedAdults = affectedAdults;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
