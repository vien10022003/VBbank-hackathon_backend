package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @Column(name = "TransactionID")
    private Integer transactionId;
    
    @Column(name = "AccountID")
    private Integer accountId;
    
    @Column(name = "TransactionType", length = 20)
    private String transactionType;
    
    @Column(name = "Amount", precision = 15, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "TransactionDate")
    private LocalDateTime transactionDate;
    
    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "TransactionMethod", length = 20)
    private String transactionMethod;
    
    @Column(name = "MerchantID")
    private Integer merchantId;
    
    @Column(name = "Currency", length = 10)
    private String currency;
    
    @Column(name = "ExchangeRate", precision = 10, scale = 4)
    private BigDecimal exchangeRate;
    
    @ManyToOne
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID", insertable = false, updatable = false)
    private Account account;
    
    // Constructors, getters, setters
    public Transaction() {}
}
