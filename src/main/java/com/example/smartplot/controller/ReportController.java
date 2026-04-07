package com.example.smartplot.controller;

import com.example.smartplot.service.ReportService;
import java.security.Principal;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/admin/reports/summary.csv")
    public ResponseEntity<String> exportAdminSummary(Principal principal) {
        String csv = reportService.exportAdminSummaryCsv(principal.getName());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=smartplot-admin-summary.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }
}
