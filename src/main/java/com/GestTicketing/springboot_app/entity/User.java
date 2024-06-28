package com.GestTicketing.springboot_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="users")
//@Data pour generer les methodes setter and getter
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

}

