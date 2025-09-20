package com.example.user_microservice.config;

import com.example.user_microservice.security.JwtAuthenticationFilter;
import com.example.user_microservice.security.CustomAuthenticationSuccessHandler;
import com.example.user_microservice.service.impl.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtAuthenticationFilter jwtRequestFilter;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                // Публичные REST эндпоинты
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users", "/api/users/create").permitAll()
                .antMatchers(HttpMethod.GET, "/api/subscriptions/{userId}").permitAll()

                // Публичные страницы и статика
                .antMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()

                // Админ-панель — только для ADMINISTRATOR и MODERATOR
                .antMatchers("/admin/**").hasAnyRole("ADMINISTRATOR", "MODERATOR")

                // Все остальные запросы — с аутентификацией
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()

                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        // JWT фильтр до стандартного логина
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
