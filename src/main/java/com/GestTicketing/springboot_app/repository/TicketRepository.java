package com.GestTicketing.springboot_app.repository;

import com.GestTicketing.springboot_app.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByOrderByImportanceDescUrgenceDesc();

}
