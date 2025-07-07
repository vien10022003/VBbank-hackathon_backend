package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Branches")
public class Branch {
    @Id
    @Column(name = "BranchID")
    private Integer branchId;
    
    @Column(name = "Address", columnDefinition = "TEXT")
    private String address;
    
    @Column(name = "ManagerID")
    private Integer managerId;
    
    @Column(name = "PhoneNumber", length = 15)
    private String phoneNumber;
    
    @OneToOne
    @JoinColumn(name = "ManagerID", referencedColumnName = "EmployeeID", insertable = false, updatable = false)
    @JsonIgnore
    private Employee manager;
    
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("branch-employees")
    private List<Employee> employees;
    
    // Constructors, getters, setters
    public Branch() {}
    
    // Getters
    public Integer getBranchId() { return branchId; }
    public String getAddress() { return address; }
    public Integer getManagerId() { return managerId; }
    public String getPhoneNumber() { return phoneNumber; }
    public Employee getManager() { return manager; }
    public List<Employee> getEmployees() { return employees; }
    
    // Setters
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
    public void setAddress(String address) { this.address = address; }
    public void setManagerId(Integer managerId) { this.managerId = managerId; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setManager(Employee manager) { this.manager = manager; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}
