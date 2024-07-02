package com.GestTicketing.springboot_app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String description;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMiseAJour;

    @ManyToOne
    private User apprenant;

    @ManyToOne
    private User formateur;

    // Option pour permettre au formateur de prioriser les tickets

    private int urgence; // 1 = faible, 2 = moyenne, 3 = élevée
    private int importance; // 1 = faible, 2 = moyenne, 3 = élevée

    // Pour suivre les progrès et améliorer la communication entre les formateurs et les apprenants
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;
}