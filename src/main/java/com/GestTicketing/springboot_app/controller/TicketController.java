package com.GestTicketing.springboot_app.controller;

import com.GestTicketing.springboot_app.entity.Commentaire;
import com.GestTicketing.springboot_app.entity.Ticket;
import com.GestTicketing.springboot_app.service.CommentaireService;
import com.GestTicketing.springboot_app.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
@Tag(name = "Système de gestion des tickets", description = "Opérations relatives aux tickets dans le système de gestion des tickets")

public class TicketController {
        private final TicketService ticketService;
        private final CommentaireService commentaireService;

        @Operation(summary = "Créer un nouveau ticket")
        @PostMapping
        public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
            return ResponseEntity.ok(ticketService.createTicket(ticket));
        }

        @Operation(summary = "Modifier un ticket")
        @PutMapping("/{id}")
        public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
            ticket.setId(id);
            return ResponseEntity.ok(ticketService.updateTicket(ticket));
        }

        @Operation(summary = "Supprimer un ticket")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
            ticketService.deleteTicket(id);
            return ResponseEntity.noContent().build();
        }

        @Operation(summary = "Voir la liste des tickets disponibles")
        @GetMapping
        public ResponseEntity<List<Ticket>> getAllTickets() {
            return ResponseEntity.ok(ticketService.getAllTickets());
        }

        @Operation(summary = "Rechercher un ticket par id")
        @GetMapping("/{id}")
        public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
            return ResponseEntity.ok(ticketService.getTicketById(id));
        }

        @Operation(summary = "Prioriser un ticket")
        @GetMapping("/prioritized")
        public ResponseEntity<List<Ticket>> getTicketsByPriorityAndUrgency() {
            return ResponseEntity.ok(ticketService.getTicketsByPriorityAndUrgency());
        }

        @Operation(summary = "Voir les commentaires associés à un ticket")
        @GetMapping("/{id}/commentaires")
        public ResponseEntity<List<Commentaire>> getCommentairesByTicketId(@PathVariable Long id) {
            return ResponseEntity.ok(commentaireService.getCommentairesByTicketId(id));
        }

        // Ajout de commentaire à un ticket (accessible aux formateurs)
        @Operation(summary = "Commenter un ticket")
        @PostMapping("/{id}/commentaires")
        public ResponseEntity<Commentaire> addCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
            Commentaire savedCommentaire = commentaireService.addCommentaire(id, commentaire, commentaire.getFormateur());
            if (savedCommentaire != null) {
                return ResponseEntity.ok(savedCommentaire);
            }
            return ResponseEntity.badRequest().build();
        }

        // Mis à jour du statut d'un ticket (accessible aux formateurs)
        @Operation(summary = "Mettre à jour le statut du ticket")
        @PutMapping("/{id}/status")
        public ResponseEntity<Ticket> updateStatus(@PathVariable Long id, @RequestParam String statut) {
            Ticket updatedTicket = ticketService.updateStatus(id, statut);
            if (updatedTicket != null) {
                return ResponseEntity.ok(updatedTicket);
            }
            return ResponseEntity.badRequest().build();
        }

    }
