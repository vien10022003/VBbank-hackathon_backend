package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Stocks")
public class Stock {
    @Id
    @Column(name = "StockID")
    private Integer stockId;
    
    @Column(name = "Symbol", length = 10)
    private String symbol;
    
    @Column(name = "CompanyName", length = 100)
    private String companyName;
    
    @Column(name = "CurrentPrice", precision = 15, scale = 2)
    private BigDecimal currentPrice;
    
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Portfolio> portfolios;
    
    // Constructors, getters, setters
    public Stock() {}
}
