package com.example.catalog_microservice.controller;


import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.model.TrackArtist;
import com.example.catalog_microservice.service.TrackArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/tracks")
@RequiredArgsConstructor
public class TrackArtistController {

    private final TrackArtistService trackArtistService;

    @PostMapping("/{trackId}/artists/{artistId}")
    public ResponseEntity<Void> addTrackArtist(@PathVariable Long trackId, @PathVariable Long artistId) {
        trackArtistService.addTrackArtist(trackId, artistId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{trackId}/artists/{artistId}")
    public ResponseEntity<Void> removeTrackArtist(@PathVariable Long trackId, @PathVariable Long artistId) {
        trackArtistService.removeTrackArtist(trackId, artistId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{trackId}/artists")
    public ResponseEntity<List<Artist>> getArtistsByTrack(@PathVariable Long trackId) {
        return ResponseEntity.ok(trackArtistService.getArtistsByTrack(trackId));
    }

    @GetMapping("/api/catalog/artists/{artistId}/tracks")
    public ResponseEntity<List<Track>> getTracksByArtist(@PathVariable Long artistId) {
        return ResponseEntity.ok(trackArtistService.getTracksByArtist(artistId));
    }
}


