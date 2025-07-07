package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ATMs")
public class ATM {
    @Id
    @Column(name = "ATMid")
    private Integer atmId;
    
    @Column(name = "Location", columnDefinition = "TEXT")
    private String location;
    
    @Column(name = "Status", length = 20)
    private String status;
    
    // Constructors, getters, setters
    public ATM() {}
    
    public Integer getAtmId() { return atmId; }
    public void setAtmId(Integer atmId) { this.atmId = atmId; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
