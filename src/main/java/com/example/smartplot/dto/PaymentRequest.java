package com.example.smartplot.dto;

import jakarta.validation.constraints.NotBlank;

public class PaymentRequest {

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    private boolean simulateSuccess;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isSimulateSuccess() {
        return simulateSuccess;
    }

    public void setSimulateSuccess(boolean simulateSuccess) {
        this.simulateSuccess = simulateSuccess;
    }
}
