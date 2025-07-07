package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(name = "CustomerID")
    private Integer customerId;
    
    @Column(name = "FirstName", length = 50)
    private String firstName;
    
    @Column(name = "LastName", length = 50)
    private String lastName;
    
    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;
    
    @Column(name = "Address", columnDefinition = "TEXT")
    private String address;
    
    @Column(name = "PhoneNumber", length = 15)
    private String phoneNumber;
    
    @Column(name = "Email", length = 100)
    private String email;
    
    @Column(name = "SSN", length = 20)
    private String ssn;
    
    @Column(name = "RegistrationDate")
    private LocalDate registrationDate;
    
    @Column(name = "Status", length = 20)
    private String status;
    
    @Column(name = "Gender", length = 10)
    private String gender;
    
    @Column(name = "MaritalStatus", length = 20)
    private String maritalStatus;
    
    @Column(name = "Occupation", length = 50)
    private String occupation;
    
    @Column(name = "IncomeLevel", precision = 15, scale = 2)
    private BigDecimal incomeLevel;
    
    @Column(name = "EducationLevel", length = 50)
    private String educationLevel;
    
    @Column(name = "Nationality", length = 50)
    private String nationality;
    
    @Column(name = "PreferredLanguage", length = 50)
    private String preferredLanguage;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-accounts")
    private List<Account> accounts;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-loans")
    private List<Loan> loans;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-insurance")
    private List<InsurancePolicy> insurancePolicies;
    
    @OneToMany(mappedBy = "linkedCustomer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-ewallet")
    private List<EWallet> eWallets;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-onlinebanking")
    private List<OnlineBanking> onlineBankingAccounts;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-portfolios")
    private List<Portfolio> portfolios;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-riskassessments")
    private List<RiskAssessment> riskAssessments;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-kyc")
    private List<KYC> kycDocuments;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("customer-creditscores")
    private List<CreditScore> creditScores;
    
    // Constructors, getters, setters
    public Customer() {}
    
    // Getters
    public Integer getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getSsn() { return ssn; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public String getStatus() { return status; }
    public String getGender() { return gender; }
    public String getMaritalStatus() { return maritalStatus; }
    public String getOccupation() { return occupation; }
    public BigDecimal getIncomeLevel() { return incomeLevel; }
    public String getEducationLevel() { return educationLevel; }
    public String getNationality() { return nationality; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public List<Account> getAccounts() { return accounts; }
    public List<Loan> getLoans() { return loans; }
    public List<InsurancePolicy> getInsurancePolicies() { return insurancePolicies; }
    public List<EWallet> getEWallets() { return eWallets; }
    public List<OnlineBanking> getOnlineBankingAccounts() { return onlineBankingAccounts; }
    public List<Portfolio> getPortfolios() { return portfolios; }
    public List<RiskAssessment> getRiskAssessments() { return riskAssessments; }
    public List<KYC> getKycDocuments() { return kycDocuments; }
    public List<CreditScore> getCreditScores() { return creditScores; }
    
    // Setters
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setSsn(String ssn) { this.ssn = ssn; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public void setStatus(String status) { this.status = status; }
    public void setGender(String gender) { this.gender = gender; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public void setIncomeLevel(BigDecimal incomeLevel) { this.incomeLevel = incomeLevel; }
    public void setEducationLevel(String educationLevel) { this.educationLevel = educationLevel; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }
    public void setLoans(List<Loan> loans) { this.loans = loans; }
    public void setInsurancePolicies(List<InsurancePolicy> insurancePolicies) { this.insurancePolicies = insurancePolicies; }
    public void setEWallets(List<EWallet> eWallets) { this.eWallets = eWallets; }
    public void setOnlineBankingAccounts(List<OnlineBanking> onlineBankingAccounts) { this.onlineBankingAccounts = onlineBankingAccounts; }
    public void setPortfolios(List<Portfolio> portfolios) { this.portfolios = portfolios; }
    public void setRiskAssessments(List<RiskAssessment> riskAssessments) { this.riskAssessments = riskAssessments; }
    public void setKycDocuments(List<KYC> kycDocuments) { this.kycDocuments = kycDocuments; }
    public void setCreditScores(List<CreditScore> creditScores) { this.creditScores = creditScores; }
}
