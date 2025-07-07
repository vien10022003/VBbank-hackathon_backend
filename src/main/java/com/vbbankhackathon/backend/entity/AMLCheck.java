package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "AMLChecks")
public class AMLCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CheckID")
    private Integer checkId;

    @ManyToOne
    @JoinColumn(name = "TransactionID", referencedColumnName = "TransactionID")
    private Transaction transaction;

    @Column(name = "CheckDate")
    private LocalDate checkDate;

    @Column(name = "Result", length = 20)
    private String result;

    // Constructors, getters, and setters
    public AMLCheck() {}

    public Integer getCheckId() { return checkId; }
    public void setCheckId(Integer checkId) { this.checkId = checkId; }

    public Transaction getTransaction() { return transaction; }
    public void setTransaction(Transaction transaction) { this.transaction = transaction; }

    public LocalDate getCheckDate() { return checkDate; }
    public void setCheckDate(LocalDate checkDate) { this.checkDate = checkDate; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
}
