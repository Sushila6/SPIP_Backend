package com.example.smartplot.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    private Integer paymentId;
    private Integer plotId;
    private String plotNumber;
    private BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionReference;
    private LocalDateTime createdAt;
    private String message;
    private PlotResponse plot;

    public PaymentResponse() {
    }

    public PaymentResponse(
            Integer paymentId,
            Integer plotId,
            String plotNumber,
            BigDecimal amount,
            String paymentMethod,
            String paymentStatus,
            String transactionReference,
            LocalDateTime createdAt,
            String message,
            PlotResponse plot
    ) {
        this.paymentId = paymentId;
        this.plotId = plotId;
        this.plotNumber = plotNumber;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionReference = transactionReference;
        this.createdAt = createdAt;
        this.message = message;
        this.plot = plot;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPlotId() {
        return plotId;
    }

    public void setPlotId(Integer plotId) {
        this.plotId = plotId;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlotResponse getPlot() {
        return plot;
    }

    public void setPlot(PlotResponse plot) {
        this.plot = plot;
    }
}
