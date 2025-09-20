package com.example.user_preferences_service.controller;

import com.example.user_preferences_service.dto.UserPlaylistDTO;
import com.example.user_preferences_service.service.UserPlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-playlists")
@RequiredArgsConstructor
public class UserPlaylistController {
    private final UserPlaylistService userPlaylistService;

    @PostMapping
    public ResponseEntity<Void> addUserToPlaylist(@RequestBody UserPlaylistDTO dto) {
        userPlaylistService.addUserToPlaylist(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserPlaylistDTO>> getUserPlaylists(@PathVariable Long userId) {
        return ResponseEntity.ok(userPlaylistService.getUserPlaylists(userId));
    }

    @DeleteMapping("/{userId}/{playlistId}")
    public ResponseEntity<Void> removeUserFromPlaylist(@PathVariable Long userId, @PathVariable Long playlistId) {
        userPlaylistService.removeUserFromPlaylist(userId, playlistId);
        return ResponseEntity.noContent().build();
    }
}

