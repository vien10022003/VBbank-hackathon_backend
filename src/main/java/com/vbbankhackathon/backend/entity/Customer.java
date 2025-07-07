package com.vbbankhackathon.backend.entity;

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
    private List<Account> accounts;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Loan> loans;
    
    // Constructors, getters, setters
    public Customer() {}
    
    // Getters and setters would go here
}
