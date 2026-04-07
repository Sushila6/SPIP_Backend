package com.example.smartplot.controller;

import com.example.smartplot.dto.PaymentRequest;
import com.example.smartplot.dto.PaymentResponse;
import com.example.smartplot.service.PaymentService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/plots/{plotId}/payment")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse processPlotPayment(
            @PathVariable Integer plotId,
            @Valid @RequestBody PaymentRequest request,
            Principal principal
    ) {
        return paymentService.processPlotPayment(plotId, request, principal.getName());
    }

    @GetMapping("/payments/my")
    public List<PaymentResponse> getMyPayments(Principal principal) {
        return paymentService.getMyPayments(principal.getName());
    }
}
