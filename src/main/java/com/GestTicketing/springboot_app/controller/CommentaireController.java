package com.GestTicketing.springboot_app.controller;

import com.GestTicketing.springboot_app.entity.Commentaire;
import com.GestTicketing.springboot_app.entity.Formateur;
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

    @PostMapping("/{ticketId}")
    public ResponseEntity<Commentaire> addCommentaire(
            @PathVariable Long ticketId,
            @RequestBody Commentaire commentaire,
            @RequestParam Long formateurId) {

        Formateur formateur = new Formateur();
        formateur.setId(formateurId);

        return ResponseEntity.ok(commentaireService.addCommentaire(ticketId, commentaire, formateur));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<List<Commentaire>> getCommentairesByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(commentaireService.getCommentairesByTicketId(ticketId));
    }
}
