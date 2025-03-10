package com.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column( name = "mobile", nullable = false, length=11)
    private String mobile;

    @Column(name = "password", nullable = false,length = 1000)
    private String password;

    @Column(name = "role", nullable = false, length =25)
    private String role;

}