package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference("stock-portfolios")
    private List<Portfolio> portfolios;
    
    // Constructors, getters, setters
    public Stock() {}
    
    // Getters
    public Integer getStockId() { return stockId; }
    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public List<Portfolio> getPortfolios() { return portfolios; }
    
    // Setters
    public void setStockId(Integer stockId) { this.stockId = stockId; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; }
    public void setPortfolios(List<Portfolio> portfolios) { this.portfolios = portfolios; }
}
