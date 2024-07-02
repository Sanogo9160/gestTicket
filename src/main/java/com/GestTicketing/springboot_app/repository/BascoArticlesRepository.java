package com.GestTicketing.springboot_app.repository;

import com.GestTicketing.springboot_app.entity.BascoArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BascoArticlesRepository extends JpaRepository<BascoArticle,Long> {

    List<BascoArticle> findByCategorie(String categorie);
}

