package com.saveme.saveme.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reporterName;
    
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Contact> reporterContacts;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private String locationDescription;

    private String incidentType; // "flood", "landslide", "traped", "other"
    private String severity;
    private boolean roadBlocked;
    private boolean isOpen = true;
    private LocalDateTime incidentTime;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Photo> photos;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Acknowledgement> acknowledgements;

    private int affectedPeople;
    private int affectedChildren;
    private int affectedAdults;
    private int affectedInjured;
    private String comments;
    private Double waterLevel; // for floods
    private int trueReports;
    private int spamReports;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public List<Contact> getReporterContacts() {
        return reporterContacts;
    }

    public void setReporterContacts(List<Contact> reporterContacts) {
        this.reporterContacts = reporterContacts;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean isRoadBlocked() {
        return roadBlocked;
    }

    public void setRoadBlocked(boolean roadBlocked) {
        this.roadBlocked = roadBlocked;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public LocalDateTime getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(LocalDateTime incidentTime) {
        this.incidentTime = incidentTime;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Acknowledgement> getAcknowledgements() {
        return acknowledgements;
    }

    public void setAcknowledgements(List<Acknowledgement> acknowledgements) {
        this.acknowledgements = acknowledgements;
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

    public int getAffectedAdults() {
        return affectedAdults;
    }

    public void setAffectedAdults(int affectedAdults) {
        this.affectedAdults = affectedAdults;
    }

    public int getAffectedInjured() {
        return affectedInjured;
    }

    public void setAffectedInjured(int affectedInjured) {
        this.affectedInjured = affectedInjured;
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

    public int getTrueReports() {
        return trueReports;
    }

    public void setTrueReports(int trueReports) {
        this.trueReports = trueReports;
    }

    public int getSpamReports() {
        return spamReports;
    }

    public void setSpamReports(int spamReports) {
        this.spamReports = spamReports;
    }
}
