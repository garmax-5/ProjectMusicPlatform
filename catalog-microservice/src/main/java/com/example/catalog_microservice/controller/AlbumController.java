package com.example.catalog_microservice.controller;

import com.example.catalog_microservice.dto.AlbumDisplayDTO;
import com.example.catalog_microservice.dto.AlbumRequestDTO;
import com.example.catalog_microservice.dto.AlbumResponseDTO;
import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catalog/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDTO>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> getAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> createAlbum(@RequestBody @Valid AlbumRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.createAlbumFromDTO(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(@PathVariable Long id, @RequestBody @Valid AlbumRequestDTO dto) {
        Album album = new Album();
        album.setAlbumName(dto.getAlbumName());
        album.setReleaseYear(dto.getReleaseYear());
        album.setAlbumTypeId(dto.getAlbumTypeId());
        album.setCoverImage(dto.getCoverImage());
        return ResponseEntity.ok(albumService.updateAlbum(id, album));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/display/{albumId}")
    public ResponseEntity<AlbumDisplayDTO> getAlbumDisplay(@PathVariable Long albumId) {
        return ResponseEntity.ok(albumService.getAlbumDisplayById(albumId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AlbumResponseDTO>> searchAlbumsByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(albumService.searchAlbumsByName(name));
    }
}

