package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CreditScores")
public class CreditScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScoreID")
    private Integer scoreId;

    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    private Customer customer;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "Date")
    private LocalDate date;

    // Constructors, getters, and setters
    public CreditScore() {}

    public Integer getScoreId() { return scoreId; }
    public void setScoreId(Integer scoreId) { this.scoreId = scoreId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
