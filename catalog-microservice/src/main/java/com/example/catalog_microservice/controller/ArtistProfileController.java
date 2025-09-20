package com.example.catalog_microservice.controller;

import com.example.catalog_microservice.dto.ArtistProfileDTO;
import com.example.catalog_microservice.dto.SimpleAlbumDTO;
import com.example.catalog_microservice.dto.SimpleTrackDTO;
import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.model.AlbumArtist;
import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog/artists")
@RequiredArgsConstructor
public class ArtistProfileController {

    private final ArtistRepository artistRepository;
    private final TrackArtistRepository trackArtistRepository;
    private final AlbumArtistRepository albumArtistRepository;
    private final AlbumRepository albumRepository; // Добавили для получения обложек

    @GetMapping("/{id}/profile")
    public ResponseEntity<ArtistProfileDTO> getArtistProfile(@PathVariable Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        // Получение треков и обложек
        List<Track> tracks = trackArtistRepository.findTracksByArtistId(id);
        List<SimpleTrackDTO> trackDTOs = tracks.stream()
                .map(track -> {
                    String coverImage = Optional.ofNullable(track.getAlbumId())
                            .flatMap(albumRepository::findById)
                            .map(Album::getCoverImage)
                            .orElse(null);
                    return new SimpleTrackDTO(track.getId(), track.getName(), coverImage);
                })
                .collect(Collectors.toList());

        // Получение альбомов
        List<Album> albums = albumArtistRepository.findByArtistId(id).stream()
                .map(AlbumArtist::getAlbum)
                .distinct()
                .collect(Collectors.toList());

        List<SimpleAlbumDTO> albumDTOs = albums.stream()
                .map(album -> new SimpleAlbumDTO(
                        album.getId(),
                        album.getAlbumName(),
                        album.getReleaseYear(),
                        album.getCoverImage()
                ))
                .collect(Collectors.toList());

        ArtistProfileDTO profile = new ArtistProfileDTO(
                artist.getId(),
                artist.getName(),
                artist.getListenerCount(),
                trackDTOs,
                albumDTOs
        );

        return ResponseEntity.ok(profile);
    }
}
