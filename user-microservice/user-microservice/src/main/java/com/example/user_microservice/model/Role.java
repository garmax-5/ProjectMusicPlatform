package com.example.user_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    @Getter @Setter
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<User> users;

    public Role() {
    }

    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        user.setRole(this); // Устанавливаем связь с пользователем
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setRole(null); // Разрываем связь с пользователем
    }

}

