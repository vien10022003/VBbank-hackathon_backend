package com.vbbankhackathon.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ExchangeRates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RateID")
    private Integer rateId;

    @Column(name = "CurrencyFrom", length = 10)
    private String currencyFrom;

    @Column(name = "CurrencyTo", length = 10)
    private String currencyTo;

    @Column(name = "Rate", precision = 10, scale = 4)
    private BigDecimal rate;

    @Column(name = "Date")
    private LocalDate date;

    // Constructors, getters, and setters
    public ExchangeRate() {}

    public Integer getRateId() { return rateId; }
    public void setRateId(Integer rateId) { this.rateId = rateId; }

    public String getCurrencyFrom() { return currencyFrom; }
    public void setCurrencyFrom(String currencyFrom) { this.currencyFrom = currencyFrom; }

    public String getCurrencyTo() { return currencyTo; }
    public void setCurrencyTo(String currencyTo) { this.currencyTo = currencyTo; }

    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
