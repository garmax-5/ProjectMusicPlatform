package com.example.user_microservice.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.Set;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMINISTRATOR") || roles.contains("ROLE_MODERATOR")) {
            log.info("Redirecting admin/moderator to /admin/dashboard");
            response.sendRedirect("/admin/dashboard");
        } else {
            log.info("Redirecting user to /tracks");
            response.sendRedirect("/tracks");
        }
    }
}
