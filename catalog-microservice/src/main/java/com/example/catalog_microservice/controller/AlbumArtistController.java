package com.example.catalog_microservice.controller;

import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.service.AlbumArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/albums")
@RequiredArgsConstructor
public class AlbumArtistController {

    private final AlbumArtistService albumArtistService;

    @PostMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> addAlbumArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumArtistService.addAlbumArtist(albumId, artistId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> removeAlbumArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumArtistService.removeAlbumArtist(albumId, artistId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{albumId}/artists")
    public ResponseEntity<List<Artist>> getArtistsByAlbum(@PathVariable Long albumId) {
        return ResponseEntity.ok(albumArtistService.getArtistsByAlbum(albumId));
    }

    @GetMapping("/artists/{artistId}/albums")
    public ResponseEntity<List<Album>> getAlbumsByArtist(@PathVariable Long artistId) {
        return ResponseEntity.ok(albumArtistService.getAlbumsByArtist(artistId));
    }
}

