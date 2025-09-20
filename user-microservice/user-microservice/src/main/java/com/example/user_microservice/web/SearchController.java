package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.ArtistProfileDTO;
import com.example.user_microservice.dto.TrackResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final CatalogClient catalogClient;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        // Сначала пытаемся найти артиста
        try {
            Long artistId = catalogClient.findArtistIdByName(query);
            return "redirect:/artists/" + artistId;
        } catch (Exception ignored) {
            // Если не найден артист — ищем треки
            List<TrackResponseDTO> tracks = catalogClient.searchTracksByName(query);
            if (tracks.isEmpty()) {
                return "redirect:/tracks?notFound";
            }
            model.addAttribute("tracks", tracks);
            model.addAttribute("searchQuery", query);
            return "track-list";
        }
    }
}
