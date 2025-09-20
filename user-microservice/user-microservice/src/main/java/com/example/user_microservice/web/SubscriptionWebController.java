package com.example.user_microservice.web;

import com.example.user_microservice.dto.SubscriptionResponseDTO;
import com.example.user_microservice.model.User;
import com.example.user_microservice.service.SubscriptionClient;
import com.example.user_microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SubscriptionWebController {

    private final UserService userService;
    private final SubscriptionClient subscriptionClient;

    @GetMapping("/subscribe")
    public String showSubscriptionPage(Model model) {
        List<SubscriptionResponseDTO> subscriptions = subscriptionClient.getAllAvailableSubscriptions();
        model.addAttribute("subscriptions", subscriptions);
        return "subscribe";
    }

    @PostMapping("/subscribe")
    public String processSubscription(@RequestParam Long subscriptionId, Principal principal, RedirectAttributes redirectAttributes) {
        Long userId = userService.getUserByEmail(principal.getName())
                .map(User::getUserId)
                .orElseThrow();
        boolean success = subscriptionClient.sendUserSubscription(userId, subscriptionId);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Подписка успешно оформлена!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Ошибка при оформлении подписки.");
        }
        return "redirect:/profile";
    }
}
