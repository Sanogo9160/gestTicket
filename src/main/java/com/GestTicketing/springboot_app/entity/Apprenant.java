package com.GestTicketing.springboot_app.entity;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Apprenant extends User {

}
