package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EWallets")
public class EWallet {
    @Id
    @Column(name = "EWalletID")
    private Integer eWalletId;
    
    @Column(name = "Provider", length = 50)
    private String provider;
    
    @Column(name = "AccountNumber", length = 50)
    private String accountNumber;
    
    @Column(name = "LinkedCustomerID")
    private Integer linkedCustomerId;
    
    @ManyToOne
    @JoinColumn(name = "LinkedCustomerID", referencedColumnName = "CustomerID", insertable = false, updatable = false)
    private Customer linkedCustomer;
    
    // Constructors, getters, setters
    public EWallet() {}
}
