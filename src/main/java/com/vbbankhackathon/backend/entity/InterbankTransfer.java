package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "InterbankTransfers")
public class InterbankTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransferID")
    private Integer transferId;

    @ManyToOne
    @JoinColumn(name = "FromAccountID", referencedColumnName = "AccountID")
    private Account fromAccount;

    @Column(name = "ToBank", length = 100)
    private String toBank;

    @Column(name = "ToAccountNumber", length = 50)
    private String toAccountNumber;

    @Column(name = "Amount", precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "Date")
    private LocalDateTime date;

    @Column(name = "Status", length = 20)
    private String status;

    // Constructors, getters, and setters
    public InterbankTransfer() {}

    public Integer getTransferId() { return transferId; }
    public void setTransferId(Integer transferId) { this.transferId = transferId; }

    public Account getFromAccount() { return fromAccount; }
    public void setFromAccount(Account fromAccount) { this.fromAccount = fromAccount; }

    public String getToBank() { return toBank; }
    public void setToBank(String toBank) { this.toBank = toBank; }

    public String getToAccountNumber() { return toAccountNumber; }
    public void setToAccountNumber(String toAccountNumber) { this.toAccountNumber = toAccountNumber; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
