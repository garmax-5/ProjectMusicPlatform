package com.example.user_microservice.web;

import com.example.user_microservice.client.PlaylistClient;
import com.example.user_microservice.dto.PlaylistDTO;
import com.example.user_microservice.dto.SubscriptionResponseDTO;
import com.example.user_microservice.dto.UserCreateRequest;
import com.example.user_microservice.model.User;
import com.example.user_microservice.service.SubscriptionClient;
import com.example.user_microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class WebUserController {

    private final UserService userService;
    private final PlaylistClient playlistClient;
    private final SubscriptionClient subscriptionClient;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserCreateRequest());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") UserCreateRequest userDto,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.createUser(userDto);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        List<PlaylistDTO> playlists = playlistClient.getUserPlaylists(user.getUserId());

        // Получение активной подписки
        List<SubscriptionResponseDTO> subs = subscriptionClient.getUserSubscriptions(user.getUserId());
        SubscriptionResponseDTO activeSub = subs.stream().findFirst().orElse(null); // или фильтровать по isActive при наличии

        model.addAttribute("user", user);
        model.addAttribute("playlists", playlists);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("subscription", activeSub);
        return "profile";
    }

    @PostMapping("/profile/create-playlist")
    public String createPlaylist(@RequestParam String name, @RequestParam Long userId) {
        PlaylistDTO dto = new PlaylistDTO(null, name, false, userId);
        PlaylistDTO created = playlistClient.createPlaylist(dto);
        playlistClient.linkPlaylistToUser(userId, created.getId());
        return "redirect:/profile";
    }
}

