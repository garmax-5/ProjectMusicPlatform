package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.GenreDTO;
import com.example.user_microservice.dto.TrackResponseDTO;
import com.example.user_microservice.dto.TrackWithCoverDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/tracks")
public class TrackController {

    private final CatalogClient catalogClient;

    public TrackController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping
    public String getAllTracks(@RequestParam(required = false) List<Long> genreIds,
                               Model model) {
        List<TrackWithCoverDTO> tracks;

        if (genreIds != null && !genreIds.isEmpty()) {
            tracks = catalogClient.getTracksWithCoversByGenres(genreIds);
        } else {
            tracks = catalogClient.getAllTracksWithCovers();
        }

        List<GenreDTO> genres = catalogClient.getAllGenres();

        model.addAttribute("tracks", tracks);
        model.addAttribute("genres", genres);
        model.addAttribute("selectedGenres", genreIds);

        return "track-list";
    }
}

