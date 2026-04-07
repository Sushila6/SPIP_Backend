package com.example.smartplot.controller;

import com.example.smartplot.dto.AdminAnalyticsResponse;
import com.example.smartplot.service.AdminAnalyticsService;
import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminAnalyticsController {

    private final AdminAnalyticsService adminAnalyticsService;

    public AdminAnalyticsController(AdminAnalyticsService adminAnalyticsService) {
        this.adminAnalyticsService = adminAnalyticsService;
    }

    @GetMapping("/admin/analytics")
    public AdminAnalyticsResponse getAdminAnalytics(Principal principal) {
        return adminAnalyticsService.getAdminAnalytics(principal.getName());
    }
}
