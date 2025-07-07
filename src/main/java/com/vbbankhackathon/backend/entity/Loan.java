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
    
    // Getters
    public Integer getLoanId() { return loanId; }
    public Integer getCustomerId() { return customerId; }
    public BigDecimal getLoanAmount() { return loanAmount; }
    public BigDecimal getInterestRate() { return interestRate; }
    public Integer getTerm() { return term; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }
    public BigDecimal getMonthlyPayment() { return monthlyPayment; }
    public String getLoanPurpose() { return loanPurpose; }
    public String getRepaymentSchedule() { return repaymentSchedule; }
    public Customer getCustomer() { return customer; }
    public List<Collateral> getCollaterals() { return collaterals; }
    
    // Setters
    public void setLoanId(Integer loanId) { this.loanId = loanId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setLoanAmount(BigDecimal loanAmount) { this.loanAmount = loanAmount; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }
    public void setTerm(Integer term) { this.term = term; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setStatus(String status) { this.status = status; }
    public void setMonthlyPayment(BigDecimal monthlyPayment) { this.monthlyPayment = monthlyPayment; }
    public void setLoanPurpose(String loanPurpose) { this.loanPurpose = loanPurpose; }
    public void setRepaymentSchedule(String repaymentSchedule) { this.repaymentSchedule = repaymentSchedule; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setCollaterals(List<Collateral> collaterals) { this.collaterals = collaterals; }
}
