package com.example.catalog_microservice.controller;

import com.example.catalog_microservice.dto.TrackRequestDTO;
import com.example.catalog_microservice.dto.TrackResponseDTO;
import com.example.catalog_microservice.dto.TrackWithCoverDTO;
import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<List<TrackResponseDTO>> getAllTracks() {
        return ResponseEntity.ok(trackService.getAllTracks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackResponseDTO> getTrackById(@PathVariable Long id) {
        return ResponseEntity.ok(trackService.getTrackById(id));
    }

    @GetMapping("/with-covers")
    public ResponseEntity<List<TrackWithCoverDTO>> getAllTracksWithCovers() {
        return ResponseEntity.ok(trackService.getAllTracksWithCovers());
    }

    @PostMapping
    public ResponseEntity<TrackResponseDTO> createTrack(@RequestBody @Valid TrackRequestDTO dto) {
        Track createdTrack = trackService.createTrackFromDTO(dto);
        return ResponseEntity.ok(trackService.convertToDTO(createdTrack));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable Long id, @RequestBody Track track) {
        return ResponseEntity.ok(trackService.updateTrack(id, track));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long id) {
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TrackResponseDTO>> searchTracksByName(@RequestParam("name") String name) {
        List<TrackResponseDTO> results = trackService.searchTracksByName(name);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/filter-by-genres")
    public ResponseEntity<List<TrackResponseDTO>> filterTracksByGenres(@RequestBody List<Long> genreIds) {
        List<Track> tracks = trackService.getTracksByGenreIds(genreIds);
        List<TrackResponseDTO> response = tracks.stream()
                .map(trackService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/filter-by-genres-with-covers")
    public ResponseEntity<List<TrackWithCoverDTO>> filterTracksWithCoversByGenres(@RequestBody List<Long> genreIds) {
        List<Track> tracks = trackService.getTracksByGenreIds(genreIds);
        List<TrackWithCoverDTO> response = tracks.stream()
                .map(trackService::convertToDTOWithCover)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
