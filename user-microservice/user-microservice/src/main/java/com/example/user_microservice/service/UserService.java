package com.example.user_microservice.service;

import com.example.grpccommon.SubscriptionServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.user_microservice.model.Role;
import com.example.user_microservice.model.User;
import com.example.user_microservice.repository.RoleRepository;
import com.example.user_microservice.repository.UserRepository;
import com.example.user_microservice.service.SubscriptionClient;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionClient subscriptionClient;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, SubscriptionClient subscriptionClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.subscriptionClient = subscriptionClient;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        log.debug("Запрос на получение пользователя с ID: {}", id);
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            log.error("Ошибка при получении пользователя с ID: {}", id, e);
            throw new RuntimeException("Ошибка при получении пользователя", e);
        }
    }

    public User createUser(User user) {
        // Получаем роль из базы данных по roleId
        Optional<Role> roleOptional = roleRepository.findById(user.getRole().getRoleId());
        if (roleOptional.isEmpty()) {
            throw new IllegalArgumentException("Роль не найдена");
        }
        Role role = roleOptional.get();
        // Устанавливаем роль пользователю
        user.setRole(role);
        // Шифруем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Сохраняем пользователя в базе данных
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean createUserSubscription(Long userId, Long subscriptionId) {
        log.info("Обработка подписки для пользователя: {}", userId);
        return subscriptionClient.sendUserSubscription(userId, subscriptionId);
    }


}

