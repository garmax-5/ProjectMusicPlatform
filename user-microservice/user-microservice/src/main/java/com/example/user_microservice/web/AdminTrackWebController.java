package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/tracks")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class AdminTrackWebController {

    private final CatalogClient catalogClient;

    @GetMapping
    public String showTrackManagementPage(Model model) {
        List<TrackResponseDTO> tracks = catalogClient.getAllTracks();
        List<GenreDTO> genres = catalogClient.getAllGenres();
        List<MediaTypeDTO> mediaTypes = catalogClient.getAllMediaTypes();

        model.addAttribute("tracks", tracks);
        model.addAttribute("genres", genres);
        model.addAttribute("mediaTypes", mediaTypes);
        model.addAttribute("newTrack", new TrackCreateDTO());

        return "admin/tracks";
    }

    @PostMapping("/create")
    public String createTrack(@ModelAttribute("newTrack") TrackCreateDTO dto,
                              @RequestParam("artistName") String artistName,
                              @RequestParam("albumId") Long albumId) {
        // Найти ID исполнителя по имени
        Long artistId = catalogClient.findArtistIdByName(artistName);
        dto.setAlbumId(albumId);

        // Создать трек
        TrackResponseDTO created = catalogClient.createTrack(dto);

        // Привязать исполнителя
        catalogClient.addTrackArtist(created.getId(), artistId);

        return "redirect:/admin/tracks";
    }

    // Получить список альбомов по имени исполнителя (AJAX)
    @GetMapping("/albums-by-artist")
    @ResponseBody
    public List<AlbumResponseDTO> getAlbumsByArtist(@RequestParam String artistName) {
        Long artistId = catalogClient.findArtistIdByName(artistName);
        return catalogClient.getAlbumsByArtist(artistId);
    }

    @GetMapping("/search")
    public String searchTrack(@RequestParam("query") String query, Model model) {
        List<TrackResponseDTO> tracks = catalogClient.searchTracksByName(query); // вызываем клиент
        List<GenreDTO> genres = catalogClient.getAllGenres();
        List<MediaTypeDTO> mediaTypes = catalogClient.getAllMediaTypes();

        model.addAttribute("tracks", catalogClient.getAllTracks()); // всё ещё нужен для формы
        model.addAttribute("genres", genres);
        model.addAttribute("mediaTypes", mediaTypes);
        model.addAttribute("newTrack", new TrackCreateDTO());
        model.addAttribute("foundTracks", tracks); // результат поиска

        return "admin/tracks";
    }

}
