package com.example.catalog_microservice.service;

import com.example.catalog_microservice.dto.ArtistProfileDTO;
import com.example.catalog_microservice.dto.SimpleAlbumDTO;
import com.example.catalog_microservice.dto.SimpleTrackDTO;
import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.model.AlbumArtist;
import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistProfileService {

    private final ArtistRepository artistRepository;
    private final TrackArtistRepository trackArtistRepository;
    private final TrackRepository trackRepository;
    private final AlbumArtistRepository albumArtistRepository;
    private final AlbumRepository albumRepository;

    public ArtistProfileService(
            ArtistRepository artistRepository,
            TrackArtistRepository trackArtistRepository,
            TrackRepository trackRepository,
            AlbumArtistRepository albumArtistRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.trackArtistRepository = trackArtistRepository;
        this.trackRepository = trackRepository;
        this.albumArtistRepository = albumArtistRepository;
        this.albumRepository = albumRepository;
    }

    public ArtistProfileDTO getArtistProfile(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Исполнитель не найден"));

        // Получение треков
        List<Track> tracks = trackArtistRepository.findTracksByArtistId(artistId);
        List<SimpleTrackDTO> trackDTOs = tracks.stream()
                .map(this::convertToSimpleTrackDTO)
                .collect(Collectors.toList());

        // Получение альбомов
        List<Album> albums = albumArtistRepository.findByArtistId(artistId).stream()
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

        // Возврат полного профиля
        return new ArtistProfileDTO(
                artist.getId(),
                artist.getName(),
                artist.getListenerCount(),
                trackDTOs,
                albumDTOs
        );
    }

    private SimpleTrackDTO convertToSimpleTrackDTO(Track track) {
        String coverImage = albumRepository.findById(track.getAlbumId())
                .map(Album::getCoverImage)
                .orElse(null); // ← получаем обложку из альбома

        return new SimpleTrackDTO(track.getId(), track.getName(), coverImage);
    }

}
