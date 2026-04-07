package com.example.smartplot.service;

import com.example.smartplot.dto.AdminAnalyticsResponse;
import com.example.smartplot.model.Plot;
import com.example.smartplot.model.User;
import com.example.smartplot.repository.PlotRepository;
import com.example.smartplot.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final UserRepository userRepository;
    private final PlotRepository plotRepository;
    private final AdminAnalyticsService adminAnalyticsService;

    public ReportService(
            UserRepository userRepository,
            PlotRepository plotRepository,
            AdminAnalyticsService adminAnalyticsService
    ) {
        this.userRepository = userRepository;
        this.plotRepository = plotRepository;
        this.adminAnalyticsService = adminAnalyticsService;
    }

    public String exportAdminSummaryCsv(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("Only ADMIN accounts can export reports");
        }

        AdminAnalyticsResponse analytics = adminAnalyticsService.getAdminAnalytics(userEmail);
        List<Plot> plots = plotRepository.findAll();

        StringBuilder csv = new StringBuilder();
        csv.append("section,metric,value\n");
        csv.append("users,total,").append(analytics.getTotalUsers()).append('\n');
        csv.append("users,admins,").append(analytics.getTotalAdmins()).append('\n');
        csv.append("users,investors,").append(analytics.getTotalInvestors()).append('\n');
        csv.append("users,regular_users,").append(analytics.getTotalRegularUsers()).append('\n');
        csv.append("plots,total,").append(analytics.getTotalPlots()).append('\n');
        csv.append("plots,approved,").append(analytics.getApprovedPlots()).append('\n');
        csv.append("plots,pending,").append(analytics.getPendingPlots()).append('\n');
        csv.append("plots,rejected,").append(analytics.getRejectedPlots()).append('\n');
        csv.append("plots,available,").append(analytics.getAvailablePlots()).append('\n');
        csv.append("plots,reserved,").append(analytics.getReservedPlots()).append('\n');
        csv.append("plots,sold,").append(analytics.getSoldPlots()).append('\n');
        csv.append("bookings,total,").append(analytics.getTotalBookings()).append('\n');
        csv.append("payments,total,").append(analytics.getTotalPayments()).append('\n');
        csv.append("payments,success,").append(analytics.getSuccessfulPayments()).append('\n');
        csv.append("payments,failed,").append(analytics.getFailedPayments()).append('\n');
        csv.append('\n');
        csv.append("plot_id,plot_number,location,status,approval_status,booked_by_email\n");

        for (Plot plot : plots) {
            csv.append(plot.getPlotId()).append(',')
                    .append(escape(plot.getPlotNumber())).append(',')
                    .append(escape(plot.getLocation())).append(',')
                    .append(escape(plot.getStatus())).append(',')
                    .append(escape(defaultApproval(plot.getApprovalStatus()))).append(',')
                    .append(escape(plot.getBookedByEmail()))
                    .append('\n');
        }

        return csv.toString();
    }

    private String defaultApproval(String approvalStatus) {
        if (approvalStatus == null || approvalStatus.isBlank()) {
            return "PENDING";
        }
        return approvalStatus;
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        String escaped = value.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}
