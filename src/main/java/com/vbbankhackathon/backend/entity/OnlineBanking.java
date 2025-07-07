package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OnlineBanking")
public class OnlineBanking {
    @Id
    @Column(name = "OnlineBankingID")
    private Integer onlineBankingId;
    
    @Column(name = "CustomerID")
    private Integer customerId;
    
    @Column(name = "Username", length = 50)
    private String username;
    
    @Column(name = "Password", length = 100)
    private String password;
    
    @Column(name = "LastLogin")
    private LocalDateTime lastLogin;
    
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", insertable = false, updatable = false)
    @JsonBackReference("customer-onlinebanking")
    private Customer customer;
    
    // Constructors, getters, setters
    public OnlineBanking() {}
    
    // Getters
    public Integer getOnlineBankingId() { return onlineBankingId; }
    public Integer getCustomerId() { return customerId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public Customer getCustomer() { return customer; }
    
    // Setters
    public void setOnlineBankingId(Integer onlineBankingId) { this.onlineBankingId = onlineBankingId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
