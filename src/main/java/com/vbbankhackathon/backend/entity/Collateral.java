package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Collateral")
public class Collateral {
    @Id
    @Column(name = "CollateralID")
    private Integer collateralId;
    
    @Column(name = "LoanID")
    private Integer loanId;
    
    @Column(name = "Type", length = 50)
    private String type;
    
    @Column(name = "Value", precision = 15, scale = 2)
    private BigDecimal value;
    
    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "LoanID", referencedColumnName = "LoanID", insertable = false, updatable = false)
    private Loan loan;
    
    // Constructors, getters, setters
    public Collateral() {}
}
