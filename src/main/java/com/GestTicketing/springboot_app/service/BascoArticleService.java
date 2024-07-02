package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.BascoArticle;
import com.GestTicketing.springboot_app.repository.BascoArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BascoArticleService {

    private final BascoArticlesRepository bascoArticlesRepository;

    public BascoArticle createArticle(BascoArticle article) {
        return bascoArticlesRepository.save(article);
    }

    public List<BascoArticle> getAllArticles() {
        return bascoArticlesRepository.findAll();
    }

    public List<BascoArticle> getArticlesByCategorie(String categorie) {
        return bascoArticlesRepository.findByCategorie(categorie);
    }

    public BascoArticle getArticleById(Long id) {
        return bascoArticlesRepository.findById(id).orElse(null);
    }

    public BascoArticle updateArticle(BascoArticle article) {
        return bascoArticlesRepository.save(article);
    }

    public void deleteArticle(Long id) {
        bascoArticlesRepository.deleteById(id);
    }
}

