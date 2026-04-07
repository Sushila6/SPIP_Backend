package com.example.smartplot.service;

import com.example.smartplot.dto.PaymentRequest;
import com.example.smartplot.dto.PaymentResponse;
import com.example.smartplot.dto.PlotResponse;
import com.example.smartplot.model.Payment;
import com.example.smartplot.model.Plot;
import com.example.smartplot.model.User;
import com.example.smartplot.repository.PaymentRepository;
import com.example.smartplot.repository.PlotRepository;
import com.example.smartplot.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PlotRepository plotRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public PaymentService(
            PaymentRepository paymentRepository,
            PlotRepository plotRepository,
            UserRepository userRepository,
            NotificationService notificationService
    ) {
        this.paymentRepository = paymentRepository;
        this.plotRepository = plotRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public PaymentResponse processPlotPayment(Integer plotId, PaymentRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        ensureBuyerRole(user);

        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));

        if (!"APPROVED".equalsIgnoreCase(plot.getApprovalStatus())) {
            throw new IllegalArgumentException("Only approved plots can be paid for");
        }

        if (!"AVAILABLE".equalsIgnoreCase(plot.getStatus())) {
            throw new IllegalArgumentException("Only available plots can be paid for and booked");
        }

        String paymentMethod = normalizeMethod(request.getPaymentMethod());
        boolean success = request.isSimulateSuccess();

        Payment payment = new Payment();
        payment.setPlotId(plot.getPlotId());
        payment.setPlotNumber(plot.getPlotNumber());
        payment.setPayerName(user.getName());
        payment.setPayerEmail(user.getEmail());
        payment.setAmount(plot.getPrice());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(success ? "SUCCESS" : "FAILED");
        payment.setTransactionReference(generateReference(success));
        payment.setCreatedAt(LocalDateTime.now());

        PlotResponse plotResponse = null;
        String message;

        if (success) {
            plot.setStatus("RESERVED");
            plot.setBookedByName(user.getName());
            plot.setBookedByEmail(user.getEmail());
            Plot savedPlot = plotRepository.save(plot);
            plotResponse = mapToPlotResponse(savedPlot);
            message = "Payment successful and plot booked";
            notificationService.createNotification(
                    user.getEmail(),
                    "Payment successful",
                    "Payment for " + savedPlot.getPlotNumber() + " succeeded and the plot is now reserved under your account.",
                    "PAYMENT"
            );
        } else {
            message = "Payment failed. Plot remains available";
            notificationService.createNotification(
                    user.getEmail(),
                    "Payment failed",
                    "Payment for " + plot.getPlotNumber() + " failed. The plot is still available for booking.",
                    "PAYMENT"
            );
        }

        Payment savedPayment = paymentRepository.save(payment);
        return mapToPaymentResponse(savedPayment, message, plotResponse);
    }

    public List<PaymentResponse> getMyPayments(String userEmail) {
        return paymentRepository.findByPayerEmailIgnoreCaseOrderByPaymentIdDesc(userEmail)
                .stream()
                .map(payment -> mapToPaymentResponse(payment, payment.getPaymentStatus(), null))
                .toList();
    }

    private User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private void ensureBuyerRole(User user) {
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("ADMIN accounts cannot make plot payments");
        }
    }

    private String normalizeMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method is required");
        }

        String normalized = paymentMethod.trim().toUpperCase(Locale.ROOT);
        if (!normalized.equals("UPI") && !normalized.equals("CARD") && !normalized.equals("NETBANKING")) {
            throw new IllegalArgumentException("Payment method must be UPI, CARD, or NETBANKING");
        }

        return normalized;
    }

    private String generateReference(boolean success) {
        String prefix = success ? "PAY" : "FAIL";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int random = ThreadLocalRandom.current().nextInt(1000, 9999);
        return prefix + "-" + timestamp + "-" + random;
    }

    private PaymentResponse mapToPaymentResponse(Payment payment, String message, PlotResponse plot) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getPlotId(),
                payment.getPlotNumber(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getTransactionReference(),
                payment.getCreatedAt(),
                message,
                plot
        );
    }

    private PlotResponse mapToPlotResponse(Plot plot) {
        return new PlotResponse(
                plot.getPlotId(),
                plot.getPlotNumber(),
                plot.getOwnerName(),
                plot.getLocation(),
                plot.getAreaSqft(),
                plot.getPrice(),
                plot.getStatus(),
                plot.getApprovalStatus(),
                plot.getImageUrl(),
                plot.getBookedByName(),
                plot.getBookedByEmail()
        );
    }
}
