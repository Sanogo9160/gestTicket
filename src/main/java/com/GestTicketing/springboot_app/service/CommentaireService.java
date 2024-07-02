package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.Commentaire;
import com.GestTicketing.springboot_app.repository.CommentaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaireService {
    private final CommentaireRepository commentaireRepository;

    public Commentaire addCommentaire(Commentaire commentaire) {
        commentaire.setDateCreation(new Date());
        return commentaireRepository.save(commentaire);
    }

    public List<Commentaire> getCommentairesByTicketId(Long ticketId) {
        return commentaireRepository.findByTicketId(ticketId);
    }
}
