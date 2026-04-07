package com.example.smartplot.service;

import com.example.smartplot.dto.NotificationResponse;
import com.example.smartplot.model.Notification;
import com.example.smartplot.repository.NotificationRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(String recipientEmail, String title, String message, String type) {
        Notification notification = new Notification();
        notification.setRecipientEmail(recipientEmail);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public List<NotificationResponse> getMyNotifications(String userEmail) {
        return notificationRepository.findByRecipientEmailIgnoreCaseOrderByNotificationIdDesc(userEmail)
                .stream()
                .map(notification -> new NotificationResponse(
                        notification.getNotificationId(),
                        notification.getTitle(),
                        notification.getMessage(),
                        notification.getType(),
                        notification.isRead(),
                        notification.getCreatedAt()
                ))
                .toList();
    }

    public NotificationResponse markAsRead(Integer notificationId, String userEmail) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        if (!userEmail.equalsIgnoreCase(notification.getRecipientEmail())) {
            throw new IllegalArgumentException("You can only update your own notifications");
        }

        notification.setRead(true);
        Notification saved = notificationRepository.save(notification);
        return new NotificationResponse(
                saved.getNotificationId(),
                saved.getTitle(),
                saved.getMessage(),
                saved.getType(),
                saved.isRead(),
                saved.getCreatedAt()
        );
    }
}
