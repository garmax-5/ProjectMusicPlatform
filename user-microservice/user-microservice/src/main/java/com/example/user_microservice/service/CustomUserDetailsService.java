package com.example.user_microservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.user_microservice.model.User;

public interface CustomUserDetailsService extends UserDetailsService {
    User loadUserByEmail(String email) throws UsernameNotFoundException;
}

