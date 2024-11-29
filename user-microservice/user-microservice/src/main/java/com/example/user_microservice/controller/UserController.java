package com.example.user_microservice.controller;


import com.example.user_microservice.dto.CreateUserSubscriptionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.user_microservice.dto.UserResponse;
import com.example.user_microservice.model.User;
import com.example.user_microservice.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(new UserResponse(user.getUserId(), user.getEmail(), user.getUserName(), user.getRole().getRoleName())))
                .orElse(ResponseEntity.status(500).build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        log.debug("Запрос на создание нового пользователя с данными: {}", user);
        try {
            User createdUser = userService.createUser(user);
            log.info("Пользователь с ID: {} успешно создан.", createdUser.getUserId());
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            log.error("Ошибка при создании пользователя с данными: {}", user, e);
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("Запрос на удаление пользователя с ID: {}", id);
        try {
            userService.deleteUser(id);
            log.info("Пользователь с ID: {} успешно удален.", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Ошибка при удалении пользователя с ID: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSubscription(@RequestBody CreateUserSubscriptionRequest request) {
        Long userId = request.getUserId();
        Long subscriptionId = request.getSubscriptionId();

        log.debug("Запрос на создание покупку подписки: {}", subscriptionId);
        boolean success = userService.createUserSubscription(userId, subscriptionId);
        if (success) {
            return ResponseEntity.ok("Подписка успешно создана!");
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}


