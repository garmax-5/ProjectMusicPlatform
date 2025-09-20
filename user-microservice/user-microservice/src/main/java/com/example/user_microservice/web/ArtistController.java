package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.ArtistProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ArtistController {

    private final CatalogClient catalogClient;

    @GetMapping("/artists/{id}")
    public String artistProfile(@PathVariable Long id, Model model) {
        ArtistProfileDTO artistProfile = catalogClient.getArtistProfile(id);
        model.addAttribute("artist", artistProfile);
        return "artist-profile";
    }
}