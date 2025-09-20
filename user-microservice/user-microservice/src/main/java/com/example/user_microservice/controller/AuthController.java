package com.example.user_microservice.controller;


import com.example.user_microservice.dto.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.user_microservice.dto.AuthRequest;
import com.example.user_microservice.model.User;
import com.example.user_microservice.security.JwtTokenUtil;
import com.example.user_microservice.service.impl.CustomUserDetailsServiceImpl;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(CustomUserDetailsServiceImpl customUserDetailsService,
                          JwtTokenUtil jwtTokenUtil,
                          PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest) {
        log.info("Попытка аутентификации пользователя: {}", authRequest.getEmail());
        User user = customUserDetailsService.loadUserByEmail(authRequest.getEmail());

        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            log.info("Аутентификация прошла успешно: {}", authRequest.getEmail());
            return ResponseEntity.ok(new AuthResponse("Bearer " + token));
        }

        log.warn("Неудачная попытка входа: {}", authRequest.getEmail());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Unauthorized"));
    }
}

