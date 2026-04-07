package com.example.smartplot.repository;

import com.example.smartplot.model.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByPayerEmailIgnoreCaseOrderByPaymentIdDesc(String payerEmail);
}
