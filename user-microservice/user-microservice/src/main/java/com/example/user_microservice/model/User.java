package com.example.user_microservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter @Setter
    private Long userId;

    @Column(name = "username", nullable = false, length = 50)
    @Getter @Setter
    private String username;

    @Column(name = "gender", length = 10)
    @Getter @Setter
    private String gender;

    @Column(name = "birthday")
    @Getter @Setter
    private LocalDate birthday;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    @Getter @Setter
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    @Getter @Setter
    private String password;

    @Column(name = "registration_date", updatable = false)
    @Getter
    private LocalDateTime registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @Getter @Setter
    private Role role;

    public User() {}

    public User(String username, String gender, LocalDate birthday, String email, String password, Role role) {
        this.username = username;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.registrationDate = LocalDateTime.now();
        this.role = role;
    }

    @PrePersist
    public void prePersist() {
        this.registrationDate = LocalDateTime.now();
    }
}


