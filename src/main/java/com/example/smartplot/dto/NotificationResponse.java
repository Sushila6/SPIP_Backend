package com.example.smartplot.dto;

import java.time.LocalDateTime;

public class NotificationResponse {

    private Integer notificationId;
    private String title;
    private String message;
    private String type;
    private boolean read;
    private LocalDateTime createdAt;

    public NotificationResponse(
            Integer notificationId,
            String title,
            String message,
            String type,
            boolean read,
            LocalDateTime createdAt
    ) {
        this.notificationId = notificationId;
        this.title = title;
        this.message = message;
        this.type = type;
        this.read = read;
        this.createdAt = createdAt;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public boolean isRead() {
        return read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
