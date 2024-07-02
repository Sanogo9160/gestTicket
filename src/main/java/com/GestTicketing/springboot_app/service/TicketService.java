package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.*;
import com.GestTicketing.springboot_app.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final NotificationService notificationService;
    private final CommentaireService commentaireService;

    // Méthode pour créer un ticket
    public Ticket createTicket(Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);
        createNotification(savedTicket, "Nouveau ticket créé: " + ticket.getTitre(), ticket.getApprenant());
        return savedTicket;
    }

    // Méthode pour mettre à jour un ticket
    public Ticket updateTicket(Ticket ticket) {
        Ticket updatedTicket = ticketRepository.save(ticket);
        createNotification(updatedTicket, "Ticket mis à jour: " + ticket.getTitre(), ticket.getFormateur());
        return updatedTicket;
    }

    // Méthode pour supprimer un ticket
    @Transactional
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        if (ticket != null) {
            // Supprimer les notifications associées
            notificationService.deleteNotificationsByTicketId(id);

            // Supprimer les commentaires associés
           commentaireService.deleteCommentairesByTicketId(id);

            // Supprimer le ticket
            ticketRepository.deleteById(id);
        }
    }

    // Méthode pour obtenir tous les tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Méthode pour obtenir un ticket par ID
    public Ticket getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            createNotification(ticket, "Ticket consulté: " + ticket.getTitre(), ticket.getApprenant());
        }
        return ticket;
    }

    //cette methode permet de classer les ticket en fonction de leur importance ou urgence
    public List<Ticket> getTicketsByPriorityAndUrgency() {
        return ticketRepository.findAllByOrderByImportanceDescUrgenceDesc();
    }

    // Ajoute de commentaire à un ticket
    public Commentaire addCommentaire(Long ticketId, Commentaire commentaire, Formateur formateur) {
        Ticket ticket = getTicketById(ticketId);
        if (ticket != null) {
            return commentaireService.addCommentaire(ticketId, commentaire, formateur);
        }
        return null;
    }

    // Mettre à jour le statut d'un ticket
    public Ticket updateStatus(Long ticketId, String statut) {
        Ticket ticket = getTicketById(ticketId);
        if (ticket != null) {
            ticket.setStatut(Statut.valueOf(statut.toUpperCase()));
            ticket.setDateMiseAJour(new Date());
            Ticket updatedTicket = ticketRepository.save(ticket);
            createNotification(updatedTicket, "Statut du ticket mis à jour: " + ticket.getTitre(), ticket.getApprenant());
            return updatedTicket;
        }
        return null;
    }

    // Méthode pour créer une notification
    private void createNotification(Ticket ticket, String message, User user) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDateCreation(new Date());
        notification.setVue(false);
        notification.setUser(user);
        notification.setTicket(ticket);
        notificationService.createNotification(notification);
    }

}