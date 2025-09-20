package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.ArtistRequestDTO;
import com.example.user_microservice.dto.ArtistResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/artists")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class AdminArtistWebController {

    private final CatalogClient catalogClient;

    @GetMapping
    public String showArtistsPage(@RequestParam(value = "name", required = false) String name, Model model) {
        List<ArtistResponseDTO> artists;
        if (name != null && !name.isBlank()) {
            artists = catalogClient.searchArtistsByName(name);
        } else {
            artists = catalogClient.getAllArtists();
        }

        model.addAttribute("artists", artists);
        model.addAttribute("newArtist", new ArtistRequestDTO());
        model.addAttribute("searchQuery", name); // чтобы сохранить введенное значение
        return "admin/artists";
    }


    @PostMapping("/create")
    public String createArtist(@ModelAttribute("newArtist") ArtistRequestDTO dto) {
        catalogClient.createArtist(dto);
        return "redirect:/admin/artists";
    }

    @PostMapping("/delete/{id}")
    public String deleteArtist(@PathVariable Long id) {
        catalogClient.deleteArtist(id);
        return "redirect:/admin/artists";
    }
}

