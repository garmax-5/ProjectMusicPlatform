package com.example.user_microservice.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.user_microservice.service.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        log.debug("Обработка запроса на ввод URI: {}", request.getRequestURI());

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtTokenUtil.extractUsername(jwt);
                log.debug("Извлеченный токен: {}", jwt);
                log.debug("Извлеченное имя пользователя из токена: {}", username);
            } catch (Exception e) {
                log.warn("Ошибка при извлечении имени пользователя из токена: {}", e.getMessage());
            }
        } else {
            log.warn("Заголовок Authorization отсутствует или не начинается с Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                log.debug("Пользователь '{}' не аутентифицирован, проверяем валидность токена.", username);

                // ✅ Используем метод, возвращающий UserDetails
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    log.debug("Аутентификация установлена в SecurityContext для пользователя: {}", username);
                } else {
                    log.warn("Невалидный или истекший токен для пользователя: {}", username);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Невалидный или истекший токен");
                    return;
                }
            } catch (Exception e) {
                log.error("Произошла ошибка при обработке токена для пользователя: {}", username, e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Невалидный токен");
                return;
            }
        } else {
            log.debug("Имя пользователя отсутствует или аутентификация уже установлена.");
        }

        chain.doFilter(request, response);
    }
}
