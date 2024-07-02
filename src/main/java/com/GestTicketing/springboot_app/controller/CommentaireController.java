package com.GestTicketing.springboot_app.controller;

import com.GestTicketing.springboot_app.entity.Commentaire;
import com.GestTicketing.springboot_app.service.CommentaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commentaires")
@RequiredArgsConstructor

public class CommentaireController {
    private final CommentaireService commentaireService;

    @PostMapping
    public ResponseEntity<Commentaire> addCommentaire(@RequestBody Commentaire commentaire) {
        return ResponseEntity.ok(commentaireService.addCommentaire(commentaire));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<List<Commentaire>> getCommentairesByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(commentaireService.getCommentairesByTicketId(ticketId));
    }
}
