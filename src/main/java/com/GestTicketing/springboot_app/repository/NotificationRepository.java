package com.GestTicketing.springboot_app.repository;

import com.GestTicketing.springboot_app.entity.Notification;
import com.GestTicketing.springboot_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUser(User user);
}
