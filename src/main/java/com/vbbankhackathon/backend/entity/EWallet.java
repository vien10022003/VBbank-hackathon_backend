package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference("customer-ewallet")
    private Customer linkedCustomer;
    
    // Constructors, getters, setters
    public EWallet() {}
    
    // Getters
    public Integer getEWalletId() { return eWalletId; }
    public String getProvider() { return provider; }
    public String getAccountNumber() { return accountNumber; }
    public Integer getLinkedCustomerId() { return linkedCustomerId; }
    public Customer getLinkedCustomer() { return linkedCustomer; }
    
    // Setters
    public void setEWalletId(Integer eWalletId) { this.eWalletId = eWalletId; }
    public void setProvider(String provider) { this.provider = provider; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setLinkedCustomerId(Integer linkedCustomerId) { this.linkedCustomerId = linkedCustomerId; }
    public void setLinkedCustomer(Customer linkedCustomer) { this.linkedCustomer = linkedCustomer; }
}
