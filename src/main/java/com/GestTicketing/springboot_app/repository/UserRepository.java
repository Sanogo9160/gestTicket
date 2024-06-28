package com.GestTicketing.springboot_app.repository;

import com.GestTicketing.springboot_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
