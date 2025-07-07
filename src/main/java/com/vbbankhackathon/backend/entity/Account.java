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
    
    // Getters
    public Integer getAccountId() { return accountId; }
    public Integer getCustomerId() { return customerId; }
    public String getAccountType() { return accountType; }
    public BigDecimal getBalance() { return balance; }
    public LocalDate getOpeningDate() { return openingDate; }
    public String getStatus() { return status; }
    public BigDecimal getInterestRate() { return interestRate; }
    public BigDecimal getCreditLimit() { return creditLimit; }
    public BigDecimal getAvailableCredit() { return availableCredit; }
    public BigDecimal getOverdraftLimit() { return overdraftLimit; }
    public BigDecimal getMinimumBalance() { return minimumBalance; }
    public Customer getCustomer() { return customer; }
    public List<Transaction> getTransactions() { return transactions; }
    public List<Card> getCards() { return cards; }
    
    // Setters
    public void setAccountId(Integer accountId) { this.accountId = accountId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }
    public void setStatus(String status) { this.status = status; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }
    public void setCreditLimit(BigDecimal creditLimit) { this.creditLimit = creditLimit; }
    public void setAvailableCredit(BigDecimal availableCredit) { this.availableCredit = availableCredit; }
    public void setOverdraftLimit(BigDecimal overdraftLimit) { this.overdraftLimit = overdraftLimit; }
    public void setMinimumBalance(BigDecimal minimumBalance) { this.minimumBalance = minimumBalance; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
    public void setCards(List<Card> cards) { this.cards = cards; }
}
