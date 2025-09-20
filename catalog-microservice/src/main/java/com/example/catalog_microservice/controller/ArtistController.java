package com.example.catalog_microservice.controller;

import com.example.catalog_microservice.dto.ArtistRequestDTO;
import com.example.catalog_microservice.dto.ArtistResponseDTO;
import com.example.catalog_microservice.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catalog/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtistDTOs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistByIdDTO(id));
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> createArtist(@RequestBody @Valid ArtistRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.createArtistFromDTO(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(@PathVariable Long id, @RequestBody @Valid ArtistRequestDTO dto) {
        return ResponseEntity.ok(artistService.updateArtistFromDTO(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Long> searchArtistIdByName(@RequestParam String name) {
        return artistService.findArtistIdByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ArtistResponseDTO>> filterArtistsByName(@RequestParam String name) {
        return ResponseEntity.ok(artistService.searchArtistsByName(name));
    }
}
