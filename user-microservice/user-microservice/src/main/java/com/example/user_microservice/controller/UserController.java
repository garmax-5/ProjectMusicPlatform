package com.example.user_microservice.controller;

import com.example.user_microservice.dto.CreateUserSubscriptionRequest;
import com.example.user_microservice.dto.UserCreateRequest;
import com.example.user_microservice.dto.UserResponse;
import com.example.user_microservice.model.User;
import com.example.user_microservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId)
                .map(user -> new UserResponse(user.getUserId(), user.getEmail(), user.getUsername(), user.getRole().getRoleName()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        log.debug("Запрос на создание нового пользователя: {}", request.getEmail());
        try {
            User createdUser = userService.createUser(request);
            UserResponse response = new UserResponse(
                    createdUser.getUserId(),
                    createdUser.getEmail(),
                    createdUser.getUsername(),
                    createdUser.getRole().getRoleName()
            );
            log.info("Пользователь создан с ID: {}", createdUser.getUserId());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.warn("Ошибка валидации при создании пользователя: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Ошибка при создании пользователя", e);
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("Запрос на удаление пользователя с ID: {}", id);
        try {
            userService.deleteUser(id);
            log.info("Пользователь с ID: {} удалён.", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Ошибка при удалении пользователя", e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSubscription(@RequestBody CreateUserSubscriptionRequest request) {
        log.debug("Запрос на создание подписки для пользователя {}", request.getUserId());
        boolean success = userService.createUserSubscription(request.getUserId(), request.getSubscriptionId());
        if (success) {
            return ResponseEntity.ok("Подписка успешно создана!");
        } else {
            return ResponseEntity.status(500).body("Не удалось создать подписку.");
        }
    }
}
