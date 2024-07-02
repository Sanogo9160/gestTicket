package com.GestTicketing.springboot_app.controller;

import com.GestTicketing.springboot_app.entity.BascoArticle;
import com.GestTicketing.springboot_app.service.BascoArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Basco")
@RequiredArgsConstructor

public class BascoArticleController {
    private final BascoArticleService bascoArticleService;

    @PostMapping("/articles")
    public ResponseEntity<BascoArticle> createArticle(@RequestBody BascoArticle article) {
        return ResponseEntity.ok(bascoArticleService.createArticle(article));
    }

    @GetMapping("/articles")
    public ResponseEntity<List<BascoArticle>> getAllArticles() {
        return ResponseEntity.ok(bascoArticleService.getAllArticles());
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<BascoArticle> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(bascoArticleService.getArticleById(id));
    }

    @GetMapping("/articles/categorie/{categorie}")
    public ResponseEntity<List<BascoArticle>> getArticlesByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(bascoArticleService.getArticlesByCategorie(categorie));
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<BascoArticle> updateArticle(@PathVariable Long id, @RequestBody BascoArticle article) {
        article.setId(id);
        return ResponseEntity.ok(bascoArticleService.updateArticle(article));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        bascoArticleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}
