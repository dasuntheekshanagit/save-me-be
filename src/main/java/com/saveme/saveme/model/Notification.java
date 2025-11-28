package com.saveme.saveme.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User reporter;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private String incidentType; // "flood", "landslide", "other"
    private String severity;
    private boolean roadBlocked;
    private LocalDateTime incidentTime;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<Photo> photos;

    private int affectedPeople;
    private int affectedChildren;
    private int affectedAdults;
    private String comments;
    private double waterLevel; // for floods
    private int trueReports;
    private int spamReports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
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
