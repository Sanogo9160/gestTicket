package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.Notification;
import com.GestTicketing.springboot_app.entity.Ticket;
import com.GestTicketing.springboot_app.entity.User;
import com.GestTicketing.springboot_app.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final NotificationService notificationService;

    public Ticket createTicket(Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);
        createNotification(savedTicket, "Nouveau ticket créé: " + ticket.getTitre(), ticket.getApprenant());
        return savedTicket;
    }

    public Ticket updateTicket(Ticket ticket) {
        Ticket updatedTicket = ticketRepository.save(ticket);
        createNotification(updatedTicket, "Ticket mis à jour: " + ticket.getTitre(), ticket.getFormateur());
        return updatedTicket;
    }

    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        if (ticket != null) {
            ticketRepository.deleteById(id);
            createNotification(ticket, "Ticket supprimé: " + ticket.getTitre(), ticket.getApprenant());
        }
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

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