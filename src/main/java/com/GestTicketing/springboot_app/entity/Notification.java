package com.GestTicketing.springboot_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    private Boolean vue;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ticket ticket;
}
