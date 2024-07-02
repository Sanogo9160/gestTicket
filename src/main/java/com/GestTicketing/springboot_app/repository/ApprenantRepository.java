package com.GestTicketing.springboot_app.repository;

import com.GestTicketing.springboot_app.entity.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

}
