package com.example.smartplot.controller;

import com.example.smartplot.dto.PlotRequest;
import com.example.smartplot.dto.PlotResponse;
import com.example.smartplot.service.PlotService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlotController {

    private final PlotService plotService;

    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @PostMapping("/plots")
    @ResponseStatus(HttpStatus.CREATED)
    public PlotResponse createPlot(@Valid @RequestBody PlotRequest request, Principal principal) {
        return plotService.createPlot(request, principal.getName());
    }

    @GetMapping("/plots")
    public List<PlotResponse> getAllPlots(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            Principal principal) {
        return plotService.getAllPlots(search, status, principal.getName());
    }

    @GetMapping("/plots/my-bookings")
    public List<PlotResponse> getMyBookings(Principal principal) {
        return plotService.getMyBookedPlots(principal.getName());
    }

    @PutMapping("/plots/{plotId}")
    public PlotResponse updatePlot(
            @PathVariable Integer plotId,
            @Valid @RequestBody PlotRequest request,
            Principal principal) {
        return plotService.updatePlot(plotId, request, principal.getName());
    }

    @PostMapping("/plots/{plotId}/book")
    public PlotResponse bookPlot(@PathVariable Integer plotId, Principal principal) {
        return plotService.bookPlot(plotId, principal.getName());
    }

    @PostMapping("/plots/{plotId}/cancel-booking")
    public PlotResponse cancelBooking(@PathVariable Integer plotId, Principal principal) {
        return plotService.cancelBooking(plotId, principal.getName());
    }

    @PostMapping("/plots/{plotId}/approve")
    public PlotResponse approvePlot(@PathVariable Integer plotId, Principal principal) {
        return plotService.approvePlot(plotId, principal.getName());
    }

    @PostMapping("/plots/{plotId}/reject")
    public PlotResponse rejectPlot(@PathVariable Integer plotId, Principal principal) {
        return plotService.rejectPlot(plotId, principal.getName());
    }

    @DeleteMapping("/plots/{plotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlot(@PathVariable Integer plotId, Principal principal) {
        plotService.deletePlot(plotId, principal.getName());
    }
}
