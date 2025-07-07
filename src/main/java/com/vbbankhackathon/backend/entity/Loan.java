package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Loans")
public class Loan {
    @Id
    @Column(name = "LoanID")
    private Integer loanId;
    
    @Column(name = "CustomerID")
    private Integer customerId;
    
    @Column(name = "LoanAmount", precision = 15, scale = 2)
    private BigDecimal loanAmount;
    
    @Column(name = "InterestRate", precision = 5, scale = 2)
    private BigDecimal interestRate;
    
    @Column(name = "Term")
    private Integer term;
    
    @Column(name = "StartDate")
    private LocalDate startDate;
    
    @Column(name = "EndDate")
    private LocalDate endDate;
    
    @Column(name = "Status", length = 20)
    private String status;
    
    @Column(name = "MonthlyPayment", precision = 15, scale = 2)
    private BigDecimal monthlyPayment;
    
    @Column(name = "LoanPurpose", length = 100)
    private String loanPurpose;
    
    @Column(name = "RepaymentSchedule", length = 50)
    private String repaymentSchedule;
    
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", insertable = false, updatable = false)
    private Customer customer;
    
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Collateral> collaterals;
    
    // Constructors, getters, setters
    public Loan() {}
}
