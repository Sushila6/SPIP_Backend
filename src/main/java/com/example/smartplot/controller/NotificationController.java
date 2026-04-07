package com.example.smartplot.controller;

import com.example.smartplot.dto.NotificationResponse;
import com.example.smartplot.service.NotificationService;
import java.security.Principal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications/my")
    public List<NotificationResponse> getMyNotifications(Principal principal) {
        return notificationService.getMyNotifications(principal.getName());
    }

    @PostMapping("/notifications/{notificationId}/read")
    public NotificationResponse markAsRead(@PathVariable Integer notificationId, Principal principal) {
        return notificationService.markAsRead(notificationId, principal.getName());
    }
}
