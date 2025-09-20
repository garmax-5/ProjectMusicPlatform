package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.client.PlaylistClient;
import com.example.user_microservice.dto.PlaylistDTO;
import com.example.user_microservice.dto.TrackInPlaylistDTO;
import com.example.user_microservice.dto.TrackResponseDTO;
import com.example.user_microservice.model.User;
import com.example.user_microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/playlists")
public class PlaylistWebController {

    private final PlaylistClient playlistClient;
    private final CatalogClient catalogClient;
    private final UserService userService;

    @GetMapping("/{playlistId}")
    public String showPlaylist(@PathVariable Long playlistId, Model model) {
        PlaylistDTO playlist = playlistClient.getPlaylistById(playlistId);
        List<TrackInPlaylistDTO> tracks = playlistClient.getTracksInPlaylist(playlistId);

        model.addAttribute("playlist", playlist);
        model.addAttribute("tracks", tracks);
        return "playlist-view"; // ИМЕННО ТАК ДОЛЖЕН НАЗЫВАТЬСЯ ШАБЛОН
    }

    @PostMapping("/{playlistId}/remove-track")
    public String removeTrackFromPlaylist(
            @PathVariable Long playlistId,
            @RequestParam Long trackId
    ) {
        playlistClient.removeTrackFromPlaylist(playlistId, trackId);
        return "redirect:/playlists/" + playlistId;
    }

    // Поиск треков по названию
    @GetMapping("/{playlistId}/search")
    public String searchTracksForPlaylist(@PathVariable Long playlistId,
                                          @RequestParam String query,
                                          Model model) {
        PlaylistDTO playlist = playlistClient.getPlaylistById(playlistId);
        List<TrackInPlaylistDTO> existingTracks = playlistClient.getTracksInPlaylist(playlistId);
        List<TrackResponseDTO> foundTracks = catalogClient.searchTracksByName(query);

        // Удаляем треки, которые уже есть в плейлисте
        Set<Long> existingTrackIds = existingTracks.stream()
                .map(TrackInPlaylistDTO::getId)
                .collect(Collectors.toSet());
        foundTracks.removeIf(track -> existingTrackIds.contains(track.getId()));

        model.addAttribute("playlist", playlist);
        model.addAttribute("tracks", existingTracks);
        model.addAttribute("foundTracks", foundTracks);
        return "playlist-view";
    }

    // Добавление трека
    @PostMapping("/{playlistId}/add-track")
    public String addTrackToPlaylist(@PathVariable Long playlistId,
                                     @RequestParam Long trackId,
                                     Principal principal) {
        User user = userService.getUserByEmail(principal.getName()).orElseThrow();
        playlistClient.addTrackToPlaylist(playlistId, trackId, user.getUserId());
        return "redirect:/playlists/" + playlistId;
    }

    @PostMapping("/{playlistId}/delete")
    public String deletePlaylist(@PathVariable Long playlistId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName()).orElseThrow();

        // Удалить связь user-playlist
        playlistClient.unlinkPlaylistFromUser(user.getUserId(), playlistId);

        // Удалить сам плейлист
        playlistClient.deletePlaylist(playlistId);

        return "redirect:/profile"; // или куда нужно
    }

    @PostMapping("/favorites/add")
    public String addToFavorites(@RequestParam Long trackId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName()).orElseThrow();
        Long userId = user.getUserId();

        // Получить или создать системный плейлист "My Favorites"
        var favorites = playlistClient.getOrCreateSystemPlaylist("My Favorites", userId);

        // Добавить трек
        playlistClient.addTrackToPlaylist(favorites.getId(), trackId, userId);

        return "redirect:/tracks"; // или на ту же страницу, где была кнопка
    }

}
