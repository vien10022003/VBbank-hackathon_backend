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
    
    // Getters
    public Integer getCollateralId() { return collateralId; }
    public Integer getLoanId() { return loanId; }
    public String getType() { return type; }
    public BigDecimal getValue() { return value; }
    public String getDescription() { return description; }
    public Loan getLoan() { return loan; }
    
    // Setters
    public void setCollateralId(Integer collateralId) { this.collateralId = collateralId; }
    public void setLoanId(Integer loanId) { this.loanId = loanId; }
    public void setType(String type) { this.type = type; }
    public void setValue(BigDecimal value) { this.value = value; }
    public void setDescription(String description) { this.description = description; }
    public void setLoan(Loan loan) { this.loan = loan; }
}
