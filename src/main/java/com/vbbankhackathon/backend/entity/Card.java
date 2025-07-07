package com.vbbankhackathon.backend.entity;

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
    private Account account;
    
    // Constructors, getters, setters
    public Card() {}
}
