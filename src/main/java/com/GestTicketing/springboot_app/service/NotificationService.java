package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.Notification;
import com.GestTicketing.springboot_app.entity.User;
import com.GestTicketing.springboot_app.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public Notification createNotification(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        sendEmailNotification(savedNotification);
        return savedNotification;
    }

    @Transactional
    public void deleteNotificationsByTicketId(Long ticketId) {
        notificationRepository.deleteByTicketId(ticketId);
    }

    public List<Notification> getNotificationsForUser(User user) {
        return notificationRepository.findByUser(user);
    }

    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setVue(true);
            notificationRepository.save(notification);
        }
    }

    private void sendEmailNotification(Notification notification) {
        String to = notification.getUser().getEmail();
        String subject = "Notification de Ticket";
        String text = notification.getMessage();
        emailService.sendEmail(to, subject, text);
    }
}