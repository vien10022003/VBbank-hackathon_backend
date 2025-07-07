package com.vbbankhackathon.backend.entity;

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
    private Customer customer;
    
    // Constructors, getters, setters
    public OnlineBanking() {}
}
