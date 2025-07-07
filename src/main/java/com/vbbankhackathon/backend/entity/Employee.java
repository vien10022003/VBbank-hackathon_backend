package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference("branch-employees")
    private Branch branch;
    
    // Constructors, getters, setters
    public Employee() {}
    
    // Getters
    public Integer getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPosition() { return position; }
    public String getDepartment() { return department; }
    public Integer getBranchId() { return branchId; }
    public LocalDate getHireDate() { return hireDate; }
    public BigDecimal getSalary() { return salary; }
    public Branch getBranch() { return branch; }
    
    // Setters
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPosition(String position) { this.position = position; }
    public void setDepartment(String department) { this.department = department; }
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public void setBranch(Branch branch) { this.branch = branch; }
}
