package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.Commentaire;
import com.GestTicketing.springboot_app.entity.Formateur;
import com.GestTicketing.springboot_app.entity.Ticket;
import com.GestTicketing.springboot_app.repository.CommentaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaireService {
    private final CommentaireRepository commentaireRepository;

    // Méthode pour ajouter un commentaire associé à un ticket par ID
    public Commentaire addCommentaire(Long ticketId, Commentaire commentaire, Formateur formateur) {
        commentaire.setDateCreation(new Date()); // Date de création actuelle
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        commentaire.setTicket(ticket); // Associer le commentaire au ticket par ID
        commentaire.setFormateur(formateur); // Associer le formateur au commentaire
        return commentaireRepository.save(commentaire);
    }

    // Méthode pour obtenir les commentaires d'un ticket par ID
    public List<Commentaire> getCommentairesByTicketId(Long ticketId) {
        return commentaireRepository.findByTicketId(ticketId);
    }

    // Méthode pour supprimer les commentaires d'un ticket par ID
    public void deleteCommentairesByTicketId(Long ticketId) {
        commentaireRepository.deleteByTicketId(ticketId);
    }
}
