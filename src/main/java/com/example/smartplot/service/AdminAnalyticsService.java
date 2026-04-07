package com.example.smartplot.service;

import com.example.smartplot.dto.AdminAnalyticsResponse;
import com.example.smartplot.model.Payment;
import com.example.smartplot.model.Plot;
import com.example.smartplot.model.User;
import com.example.smartplot.repository.PaymentRepository;
import com.example.smartplot.repository.PlotRepository;
import com.example.smartplot.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminAnalyticsService {

    private final UserRepository userRepository;
    private final PlotRepository plotRepository;
    private final PaymentRepository paymentRepository;

    public AdminAnalyticsService(
            UserRepository userRepository,
            PlotRepository plotRepository,
            PaymentRepository paymentRepository
    ) {
        this.userRepository = userRepository;
        this.plotRepository = plotRepository;
        this.paymentRepository = paymentRepository;
    }

    public AdminAnalyticsResponse getAdminAnalytics(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("Only ADMIN accounts can view analytics");
        }

        List<User> users = userRepository.findAll();
        List<Plot> plots = plotRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();

        long totalAdmins = users.stream()
                .filter(item -> "ADMIN".equalsIgnoreCase(item.getRole()))
                .count();
        long totalInvestors = users.stream()
                .filter(item -> "INVESTOR".equalsIgnoreCase(item.getRole()))
                .count();
        long totalRegularUsers = users.stream()
                .filter(item -> "USER".equalsIgnoreCase(item.getRole()))
                .count();

        long approvedPlots = plots.stream()
                .filter(item -> "APPROVED".equalsIgnoreCase(defaultApproval(item.getApprovalStatus())))
                .count();
        long pendingPlots = plots.stream()
                .filter(item -> "PENDING".equalsIgnoreCase(defaultApproval(item.getApprovalStatus())))
                .count();
        long rejectedPlots = plots.stream()
                .filter(item -> "REJECTED".equalsIgnoreCase(defaultApproval(item.getApprovalStatus())))
                .count();
        long availablePlots = plots.stream()
                .filter(item -> "AVAILABLE".equalsIgnoreCase(item.getStatus()))
                .count();
        long reservedPlots = plots.stream()
                .filter(item -> "RESERVED".equalsIgnoreCase(item.getStatus()))
                .count();
        long soldPlots = plots.stream()
                .filter(item -> "SOLD".equalsIgnoreCase(item.getStatus()))
                .count();
        long totalBookings = plots.stream()
                .filter(item -> item.getBookedByEmail() != null && !item.getBookedByEmail().isBlank())
                .count();
        long successfulPayments = payments.stream()
                .filter(item -> "SUCCESS".equalsIgnoreCase(item.getPaymentStatus()))
                .count();
        long failedPayments = payments.stream()
                .filter(item -> "FAILED".equalsIgnoreCase(item.getPaymentStatus()))
                .count();

        return new AdminAnalyticsResponse(
                users.size(),
                totalAdmins,
                totalInvestors,
                totalRegularUsers,
                plots.size(),
                approvedPlots,
                pendingPlots,
                rejectedPlots,
                availablePlots,
                reservedPlots,
                soldPlots,
                totalBookings,
                payments.size(),
                successfulPayments,
                failedPayments
        );
    }

    private String defaultApproval(String approvalStatus) {
        if (approvalStatus == null || approvalStatus.isBlank()) {
            return "PENDING";
        }

        return approvalStatus;
    }
}
