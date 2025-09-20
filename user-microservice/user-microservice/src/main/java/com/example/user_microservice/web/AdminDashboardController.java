package com.example.user_microservice.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class AdminDashboardController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "admin/dashboard";
    }
}

