package com.vbbankhackathon.backend.entity;

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
    private Employee manager;
    
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;
    
    // Constructors, getters, setters
    public Branch() {}
}
