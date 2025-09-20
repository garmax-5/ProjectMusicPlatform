package com.example.catalog_microservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll() // ✅ Временно разрешить доступ ко всем endpoint'ам
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//
//                // Статические ресурсы
//                .antMatchers("/images/**", "/css/**", "/js/**").permitAll()
//
//                // Публичные API
//                .antMatchers(
//                        "/api/catalog/tracks",
//                        "/api/catalog/tracks/**",
//                        "/api/catalog/artists/search"
//                ).permitAll()
//                .antMatchers(HttpMethod.GET, "/api/catalog/artists/*/profile").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/catalog/albums/display/**").permitAll()
//
//                //  Временно разрешаем доступ
//                .antMatchers(HttpMethod.GET, "/api/genres", "/api/genres/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/catalog/media-types", "/api/catalog/media-types/**").permitAll()
//
//                // Остальные требуют авторизации
//                .antMatchers("/api/catalog/**").authenticated()
//                .anyRequest().denyAll()
//
//                .and()
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
}
