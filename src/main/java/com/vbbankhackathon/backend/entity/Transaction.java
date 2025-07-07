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
    
    // Getters
    public Integer getTransactionId() { return transactionId; }
    public Integer getAccountId() { return accountId; }
    public String getTransactionType() { return transactionType; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public String getDescription() { return description; }
    public String getTransactionMethod() { return transactionMethod; }
    public Integer getMerchantId() { return merchantId; }
    public String getCurrency() { return currency; }
    public BigDecimal getExchangeRate() { return exchangeRate; }
    public Account getAccount() { return account; }
    
    // Setters
    public void setTransactionId(Integer transactionId) { this.transactionId = transactionId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public void setDescription(String description) { this.description = description; }
    public void setTransactionMethod(String transactionMethod) { this.transactionMethod = transactionMethod; }
    public void setMerchantId(Integer merchantId) { this.merchantId = merchantId; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setExchangeRate(BigDecimal exchangeRate) { this.exchangeRate = exchangeRate; }
    public void setAccount(Account account) { this.account = account; }
}
