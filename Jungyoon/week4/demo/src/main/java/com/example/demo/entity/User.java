package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login_id", nullable = false, unique = true, length = 50)
    private String loginId;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(unique = true, length = 50)
    private String nickname;

    @Column(name = "created_at", updatable = false,
    columnDefinition = " TIMESTAMP_DEFAULT_CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
