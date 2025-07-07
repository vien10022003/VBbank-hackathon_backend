package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @Column(name = "AccountID")
    private Integer accountId;
    
    @Column(name = "CustomerID")
    private Integer customerId;
    
    @Column(name = "AccountType", length = 20)
    private String accountType;
    
    @Column(name = "Balance", precision = 15, scale = 2)
    private BigDecimal balance;
    
    @Column(name = "OpeningDate")
    private LocalDate openingDate;
    
    @Column(name = "Status", length = 20)
    private String status;
    
    @Column(name = "InterestRate", precision = 5, scale = 2)
    private BigDecimal interestRate;
    
    @Column(name = "CreditLimit", precision = 15, scale = 2)
    private BigDecimal creditLimit;
    
    @Column(name = "AvailableCredit", precision = 15, scale = 2)
    private BigDecimal availableCredit;
    
    @Column(name = "OverdraftLimit", precision = 15, scale = 2)
    private BigDecimal overdraftLimit;
    
    @Column(name = "MinimumBalance", precision = 15, scale = 2)
    private BigDecimal minimumBalance;
    
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", insertable = false, updatable = false)
    private Customer customer;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> cards;
    
    // Constructors, getters, setters
    public Account() {}
}
