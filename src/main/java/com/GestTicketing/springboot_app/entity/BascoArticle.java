package com.GestTicketing.springboot_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Basco_articles")
@Data
public class BascoArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Lob
    private String contenu;
    private String categorie;

}
