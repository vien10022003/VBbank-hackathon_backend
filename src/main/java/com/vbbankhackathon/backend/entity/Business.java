package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BusinessID")
    private Integer businessId;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "TaxID", length = 50)
    private String taxId;

    @Column(name = "Address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "ContactPerson", length = 100)
    private String contactPerson;

    // Constructors, getters, and setters
    public Business() {}

    public Integer getBusinessId() { return businessId; }
    public void setBusinessId(Integer businessId) { this.businessId = businessId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
}
