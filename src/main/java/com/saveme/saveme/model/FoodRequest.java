package com.saveme.saveme.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FoodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requesterName;
    private String contactNumber;
    private String requestDetails; // e.g., "Need baby food and water for 3 people"
    private boolean isFulfilled = false;
    private LocalDateTime requestTime;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @OneToMany(mappedBy = "foodRequest", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FoodRequestHelper> helpers;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getRequestDetails() { return requestDetails; }
    public void setRequestDetails(String requestDetails) { this.requestDetails = requestDetails; }
    public boolean isFulfilled() { return isFulfilled; }
    public void setFulfilled(boolean fulfilled) { isFulfilled = fulfilled; }
    public LocalDateTime getRequestTime() { return requestTime; }
    public void setRequestTime(LocalDateTime requestTime) { this.requestTime = requestTime; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public List<FoodRequestHelper> getHelpers() { return helpers; }
    public void setHelpers(List<FoodRequestHelper> helpers) { this.helpers = helpers; }
}
