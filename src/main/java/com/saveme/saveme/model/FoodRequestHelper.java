package com.saveme.saveme.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class FoodRequestHelper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String helperName;
    private String helperContact;

    @ManyToOne
    @JoinColumn(name = "food_request_id")
    @JsonBackReference
    private FoodRequest foodRequest;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getHelperName() { return helperName; }
    public void setHelperName(String helperName) { this.helperName = helperName; }
    public String getHelperContact() { return helperContact; }
    public void setHelperContact(String helperContact) { this.helperContact = helperContact; }
    public FoodRequest getFoodRequest() { return foodRequest; }
    public void setFoodRequest(FoodRequest foodRequest) { this.foodRequest = foodRequest; }
}
