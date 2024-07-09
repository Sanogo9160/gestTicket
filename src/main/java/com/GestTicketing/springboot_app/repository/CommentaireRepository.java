package com.GestTicketing.springboot_app.repository;

import com.GestTicketing.springboot_app.entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {

    List<Commentaire> findByTicketId(Long ticketId);

    void deleteByTicketId(Long ticketId);
}
