package com.example.user_preferences_service.controller;

import com.example.user_preferences_service.dto.AddTrackRequestDTO;
import com.example.user_preferences_service.dto.TrackDisplayDTO;
import com.example.user_preferences_service.service.PlaylistTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences/playlists")
@RequiredArgsConstructor
public class PlaylistTrackController {

    private final PlaylistTrackService playlistTrackService;

    @GetMapping("/{playlistId}/tracks")
    public ResponseEntity<List<TrackDisplayDTO>> getTracks(@PathVariable Long playlistId) {
        return ResponseEntity.ok(playlistTrackService.getTracksInPlaylist(playlistId));
    }

    @DeleteMapping("/{playlistId}/tracks/{trackId}")
    public ResponseEntity<Void> removeTrack(@PathVariable Long playlistId, @PathVariable Long trackId) {
        playlistTrackService.removeTrackFromPlaylist(playlistId, trackId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playlistId}/tracks")
    public ResponseEntity<Void> addTrack(@RequestBody AddTrackRequestDTO dto) {
        playlistTrackService.addTrackToPlaylist(dto.getPlaylistId(), dto.getTrackId(), dto.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

