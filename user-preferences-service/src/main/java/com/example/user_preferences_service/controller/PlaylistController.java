package com.example.user_preferences_service.controller;

import com.example.user_preferences_service.dto.PlaylistDTO;
import com.example.user_preferences_service.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/preferences/playlists")
@RequiredArgsConstructor
@Validated
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@Valid @RequestBody PlaylistDTO dto) {
        PlaylistDTO created = playlistService.createPlaylist(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PlaylistDTO>> getUserPlaylists(@PathVariable Long userId) {
        return ResponseEntity.ok(playlistService.getPlaylistsByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlaylistDTO>> getPlaylistsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(playlistService.getPlaylistsByUser(userId));
    }

    @GetMapping("/by-id/{playlistId}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable Long playlistId) {
        return ResponseEntity.ok(playlistService.getPlaylistById(playlistId));
    }
}
