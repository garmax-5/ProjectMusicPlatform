package com.example.user_microservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter
    @Setter
    private Long userId;

    @Column(name = "username", nullable = false, length = 50)
    @Setter
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
    @Setter
    private String password;

    @Column(name = "registration_date", updatable = false)
    @Getter
    private LocalDateTime registrationDate;

//    @Column(name = "role_id", nullable = false, length = 255)
//    @Getter @Setter
//    private int role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @Getter @Setter
    private Role role;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<UserSubscription> subscriptions = new HashSet<>();

    public User(String username, String gender, LocalDate birthday, String email, String password, Role role) {
        this.username = username;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.registrationDate = LocalDateTime.now();
        this.role = role;
    }

    public User() {
    }

    public User(String userEmail) {
    }

    @PrePersist
    public void prePersist() {
        this.registrationDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();  // Возвращаем пустой список без ролей
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    public String getUserName() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

