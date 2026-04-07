package com.example.smartplot.repository;

import com.example.smartplot.model.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByRecipientEmailIgnoreCaseOrderByNotificationIdDesc(String recipientEmail);
}
