package com.example.smartplot.service;

import com.example.smartplot.dto.PlotRequest;
import com.example.smartplot.dto.PlotResponse;
import com.example.smartplot.model.Plot;
import com.example.smartplot.model.User;
import com.example.smartplot.repository.PlotRepository;
import com.example.smartplot.repository.UserRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PlotService {

    private final PlotRepository plotRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public PlotService(PlotRepository plotRepository, UserRepository userRepository, NotificationService notificationService) {
        this.plotRepository = plotRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public PlotResponse createPlot(PlotRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        ensureAdmin(user);

        String plotNumber = request.getPlotNumber().trim();

        if (plotRepository.existsByPlotNumber(plotNumber)) {
            throw new IllegalArgumentException("Plot number already exists");
        }

        Plot plot = new Plot();
        plot.setPlotNumber(plotNumber);
        plot.setOwnerName(request.getOwnerName().trim());
        plot.setLocation(request.getLocation().trim());
        plot.setAreaSqft(request.getAreaSqft());
        plot.setPrice(request.getPrice());
        plot.setStatus(normalizePlotStatus(request.getStatus()));
        plot.setApprovalStatus("PENDING");
        plot.setImageUrl(normalizeImageUrl(request.getImageUrl()));

        Plot savedPlot = plotRepository.save(plot);
        notificationService.createNotification(
                user.getEmail(),
                "Plot created",
                savedPlot.getPlotNumber() + " has been created and is waiting for approval.",
                "PLOT"
        );
        return mapToResponse(savedPlot);
    }

    public List<PlotResponse> getAllPlots(String search, String status, String userEmail) {
        User user = getUserByEmail(userEmail);
        String normalizedSearch = search == null ? "" : search.trim().toLowerCase();
        String normalizedStatus = status == null ? "" : status.trim().toUpperCase();

        return plotRepository.findAll(Sort.by(Sort.Direction.DESC, "plotId"))
                .stream()
                .filter(plot -> canViewPlot(user, plot))
                .filter(plot -> matchesSearch(plot, normalizedSearch))
                .filter(plot -> matchesStatus(plot, normalizedStatus))
                .map(this::mapToResponse)
                .toList();
    }

    public PlotResponse updatePlot(Integer plotId, PlotRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        ensureAdmin(user);

        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));

        String plotNumber = request.getPlotNumber().trim();
        if (plotRepository.existsByPlotNumberAndPlotIdNot(plotNumber, plotId)) {
            throw new IllegalArgumentException("Plot number already exists");
        }

        plot.setPlotNumber(plotNumber);
        plot.setOwnerName(request.getOwnerName().trim());
        plot.setLocation(request.getLocation().trim());
        plot.setAreaSqft(request.getAreaSqft());
        plot.setPrice(request.getPrice());
        plot.setStatus(normalizePlotStatus(request.getStatus()));
        plot.setApprovalStatus("PENDING");
        plot.setImageUrl(normalizeImageUrl(request.getImageUrl()));

        Plot savedPlot = plotRepository.save(plot);
        notificationService.createNotification(
                user.getEmail(),
                "Plot updated",
                savedPlot.getPlotNumber() + " was updated and moved back to pending approval.",
                "PLOT"
        );
        return mapToResponse(savedPlot);
    }

    public PlotResponse bookPlot(Integer plotId, String userEmail) {
        User user = getUserByEmail(userEmail);

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("ADMIN accounts cannot book plots");
        }

        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));

        if (!"APPROVED".equalsIgnoreCase(plot.getApprovalStatus())) {
            throw new IllegalArgumentException("Only approved plots can be booked");
        }

        if (!"AVAILABLE".equalsIgnoreCase(plot.getStatus())) {
            throw new IllegalArgumentException("Only available plots can be booked");
        }

        plot.setStatus("RESERVED");
        plot.setBookedByName(user.getName());
        plot.setBookedByEmail(user.getEmail());

        Plot savedPlot = plotRepository.save(plot);
        return mapToResponse(savedPlot);
    }

    public List<PlotResponse> getMyBookedPlots(String userEmail) {
        return plotRepository.findAll(Sort.by(Sort.Direction.DESC, "plotId"))
                .stream()
                .filter(plot -> userEmail.equalsIgnoreCase(plot.getBookedByEmail()))
                .map(this::mapToResponse)
                .toList();
    }

    public PlotResponse cancelBooking(Integer plotId, String userEmail) {
        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));

        if (plot.getBookedByEmail() == null || !userEmail.equalsIgnoreCase(plot.getBookedByEmail())) {
            throw new IllegalArgumentException("You can only cancel your own booking");
        }

        plot.setStatus("AVAILABLE");
        plot.setBookedByName(null);
        plot.setBookedByEmail(null);

        Plot savedPlot = plotRepository.save(plot);
        notificationService.createNotification(
                userEmail,
                "Booking cancelled",
                "Your booking for " + savedPlot.getPlotNumber() + " has been cancelled and the plot is available again.",
                "BOOKING"
        );
        return mapToResponse(savedPlot);
    }

    public PlotResponse approvePlot(Integer plotId, String userEmail) {
        User user = getUserByEmail(userEmail);
        ensureAdmin(user);

        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));

        plot.setApprovalStatus("APPROVED");
        Plot savedPlot = plotRepository.save(plot);
        notificationService.createNotification(
                user.getEmail(),
                "Plot approved",
                savedPlot.getPlotNumber() + " is now approved and visible in the buyer catalogue.",
                "APPROVAL"
        );
        return mapToResponse(savedPlot);
    }

    public PlotResponse rejectPlot(Integer plotId, String userEmail) {
        User user = getUserByEmail(userEmail);
        ensureAdmin(user);

        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));

        if ("RESERVED".equalsIgnoreCase(plot.getStatus()) || "SOLD".equalsIgnoreCase(plot.getStatus())) {
            throw new IllegalArgumentException("Reserved or sold plots cannot be rejected");
        }

        plot.setApprovalStatus("REJECTED");
        Plot savedPlot = plotRepository.save(plot);
        notificationService.createNotification(
                user.getEmail(),
                "Plot rejected",
                savedPlot.getPlotNumber() + " has been rejected and removed from buyer visibility.",
                "APPROVAL"
        );
        return mapToResponse(savedPlot);
    }

    public void deletePlot(Integer plotId, String userEmail) {
        User user = getUserByEmail(userEmail);
        ensureAdmin(user);

        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));
        plotRepository.delete(plot);
    }

    private User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private void ensureAdmin(User user) {
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("Only ADMIN accounts can manage plots");
        }
    }

    private boolean matchesSearch(Plot plot, String search) {
        if (search.isEmpty()) {
            return true;
        }

        return plot.getPlotNumber().toLowerCase().contains(search)
                || plot.getOwnerName().toLowerCase().contains(search)
                || plot.getLocation().toLowerCase().contains(search)
                || plot.getStatus().toLowerCase().contains(search)
                || plot.getApprovalStatus().toLowerCase().contains(search);
    }

    private boolean matchesStatus(Plot plot, String status) {
        if (status.isEmpty() || "ALL".equals(status)) {
            return true;
        }

        return plot.getStatus().equalsIgnoreCase(status);
    }

    private PlotResponse mapToResponse(Plot plot) {
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

    private String normalizePlotStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }

        String normalized = status.trim().toUpperCase();
        if (!normalized.equals("AVAILABLE") && !normalized.equals("RESERVED") && !normalized.equals("SOLD")) {
            throw new IllegalArgumentException("Status must be AVAILABLE, RESERVED, or SOLD");
        }

        return normalized;
    }

    private boolean canViewPlot(User user, Plot plot) {
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return true;
        }

        return "APPROVED".equalsIgnoreCase(plot.getApprovalStatus());
    }

    private String normalizeImageUrl(String imageUrl) {
        if (imageUrl == null) {
            return null;
        }

        String trimmed = imageUrl.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
