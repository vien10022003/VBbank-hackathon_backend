package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "KYC")
public class KYC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KYCID")
    private Integer kycId;

    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    @JsonBackReference("customer-kyc")
    private Customer customer;

    @Column(name = "DocumentType", length = 50)
    private String documentType;

    @Column(name = "DocumentNumber", length = 50)
    private String documentNumber;

    @Column(name = "IssueDate")
    private LocalDate issueDate;

    @Column(name = "ExpiryDate")
    private LocalDate expiryDate;

    @Column(name = "VerificationStatus", length = 20)
    private String verificationStatus;

    // Constructors, getters, and setters
    public KYC() {}

    public Integer getKycId() { return kycId; }
    public void setKycId(Integer kycId) { this.kycId = kycId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }

}
