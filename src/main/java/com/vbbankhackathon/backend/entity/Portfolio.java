package com.vbbankhackathon.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference("customer-portfolios")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "StockID", referencedColumnName = "StockID", insertable = false, updatable = false)
    @JsonBackReference("stock-portfolios")
    private Stock stock;
    
    // Constructors, getters, setters
    public Portfolio() {}
    
    // Getters
    public Integer getPortfolioId() { return portfolioId; }
    public Integer getCustomerId() { return customerId; }
    public Integer getStockId() { return stockId; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public Customer getCustomer() { return customer; }
    public Stock getStock() { return stock; }
    
    // Setters
    public void setPortfolioId(Integer portfolioId) { this.portfolioId = portfolioId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setStockId(Integer stockId) { this.stockId = stockId; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setStock(Stock stock) { this.stock = stock; }
}
