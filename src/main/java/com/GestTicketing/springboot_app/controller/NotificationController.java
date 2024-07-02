package com.GestTicketing.springboot_app.controller;

import com.GestTicketing.springboot_app.entity.Notification;
import com.GestTicketing.springboot_app.entity.User;
import com.GestTicketing.springboot_app.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

        private final NotificationService notificationService;

        @PostMapping
        public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
            return ResponseEntity.ok(notificationService.createNotification(notification));
        }

        @GetMapping
        public ResponseEntity<List<Notification>> getNotificationsForUser(@AuthenticationPrincipal UserDetails userDetails) {
            User user = (User) userDetails;
            return ResponseEntity.ok(notificationService.getNotificationsForUser(user));
        }

        @PutMapping("/{id}/mark-as-read")
        public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
            notificationService.markAsRead(id);
            return ResponseEntity.noContent().build();
        }
    }