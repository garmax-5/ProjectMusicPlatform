package com.example.user_microservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.user_microservice.security.JwtAuthenticationFilter;
import com.example.user_microservice.service.impl.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtRequestFilter;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtRequestFilter, CustomUserDetailsServiceImpl customUserDetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Указываем сервис для аутентификации пользователей без учета ролей
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Отключаем CSRF (если не используется)
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll() // Разрешаем доступ к аутентификации
                .antMatchers(HttpMethod.POST, "/api/users/create").permitAll() // Разрешаем регистрацию без аутентификации
                .antMatchers(HttpMethod.GET, "/api/subscriptions/{userId}").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/subscriptions/subscribe").permitAll()
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Добавляем фильтр JWT
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Используем BCrypt для кодирования паролей
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

