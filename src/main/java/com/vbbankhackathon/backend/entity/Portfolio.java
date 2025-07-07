package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Portfolios")
public class Portfolio {
    @Id
    @Column(name = "PortfolioID")
    private Integer portfolioId;
    
    @Column(name = "CustomerID")
    private Integer customerId;
    
    @Column(name = "StockID")
    private Integer stockId;
    
    @Column(name = "Quantity")
    private Integer quantity;
    
    @Column(name = "PurchasePrice", precision = 15, scale = 2)
    private BigDecimal purchasePrice;
    
    @Column(name = "PurchaseDate")
    private LocalDate purchaseDate;
    
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", insertable = false, updatable = false)
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "StockID", referencedColumnName = "StockID", insertable = false, updatable = false)
    private Stock stock;
    
    // Constructors, getters, setters
    public Portfolio() {}
}
