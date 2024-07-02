package com.GestTicketing.springboot_app.controller;

import com.GestTicketing.springboot_app.entity.Commentaire;
import com.GestTicketing.springboot_app.entity.Ticket;
import com.GestTicketing.springboot_app.service.CommentaireService;
import com.GestTicketing.springboot_app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
        private final TicketService ticketService;
    private final CommentaireService commentaireService;

    @PostMapping
        public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
            return ResponseEntity.ok(ticketService.createTicket(ticket));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
            ticket.setId(id);
            return ResponseEntity.ok(ticketService.updateTicket(ticket));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
            ticketService.deleteTicket(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping
        public ResponseEntity<List<Ticket>> getAllTickets() {
            return ResponseEntity.ok(ticketService.getAllTickets());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
            return ResponseEntity.ok(ticketService.getTicketById(id));
        }

        @GetMapping("/prioritized")
        public ResponseEntity<List<Ticket>> getTicketsByPriorityAndUrgency() {
            return ResponseEntity.ok(ticketService.getTicketsByPriorityAndUrgency());
        }

        @GetMapping("/{id}/commentaires")
        public ResponseEntity<List<Commentaire>> getCommentairesByTicketId(@PathVariable Long id) {
            return ResponseEntity.ok(commentaireService.getCommentairesByTicketId(id));
        }

    }
