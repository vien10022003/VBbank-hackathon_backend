package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DebtCollection")
public class DebtCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DebtID")
    private Integer debtId;

    @ManyToOne
    @JoinColumn(name = "LoanID", referencedColumnName = "LoanID")
    private Loan loan;

    @Column(name = "AmountDue", precision = 15, scale = 2)
    private BigDecimal amountDue;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "Status", length = 20)
    private String status;

    // Constructors, getters, and setters
    public DebtCollection() {}

    public Integer getDebtId() { return debtId; }
    public void setDebtId(Integer debtId) { this.debtId = debtId; }

    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }

    public BigDecimal getAmountDue() { return amountDue; }
    public void setAmountDue(BigDecimal amountDue) { this.amountDue = amountDue; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}