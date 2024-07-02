package com.GestTicketing.springboot_app.repository;

import com.GestTicketing.springboot_app.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {


}
