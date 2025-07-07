package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Cards")
public class Card {
    @Id
    @Column(name = "CardID")
    private Integer cardId;
    
    @Column(name = "AccountID")
    private Integer accountId;
    
    @Column(name = "CardNumber", length = 20)
    private String cardNumber;
    
    @Column(name = "ExpirationDate")
    private LocalDate expirationDate;
    
    @Column(name = "CVV", length = 4)
    private String cvv;
    
    @Column(name = "CardType", length = 20)
    private String cardType;
    
    @Column(name = "Status", length = 20)
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID", insertable = false, updatable = false)
    @JsonBackReference("account-cards")
    private Account account;
    
    // Constructors, getters, setters
    public Card() {}
    
    // Getters
    public Integer getCardId() { return cardId; }
    public Integer getAccountId() { return accountId; }
    public String getCardNumber() { return cardNumber; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public String getCvv() { return cvv; }
    public String getCardType() { return cardType; }
    public String getStatus() { return status; }
    public Account getAccount() { return account; }
    
    // Setters
    public void setCardId(Integer cardId) { this.cardId = cardId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public void setStatus(String status) { this.status = status; }
    public void setAccount(Account account) { this.account = account; }
}
