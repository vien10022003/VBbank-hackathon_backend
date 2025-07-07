package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    private Integer employeeId;
    
    @Column(name = "FirstName", length = 50)
    private String firstName;
    
    @Column(name = "LastName", length = 50)
    private String lastName;
    
    @Column(name = "Position", length = 50)
    private String position;
    
    @Column(name = "Department", length = 50)
    private String department;
    
    @Column(name = "BranchID")
    private Integer branchId;
    
    @Column(name = "HireDate")
    private LocalDate hireDate;
    
    @Column(name = "Salary", precision = 15, scale = 2)
    private BigDecimal salary;
    
    @ManyToOne
    @JoinColumn(name = "BranchID", referencedColumnName = "BranchID", insertable = false, updatable = false)
    private Branch branch;
    
    // Constructors, getters, setters
    public Employee() {}
}
