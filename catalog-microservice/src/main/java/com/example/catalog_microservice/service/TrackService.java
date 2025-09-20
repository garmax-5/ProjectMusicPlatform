package com.example.catalog_microservice.service;

import com.example.catalog_microservice.dto.TrackRequestDTO;
import com.example.catalog_microservice.dto.TrackResponseDTO;
import com.example.catalog_microservice.dto.TrackWithCoverDTO;
import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.model.TrackArtist;
import com.example.catalog_microservice.repository.AlbumRepository;
import com.example.catalog_microservice.repository.ArtistRepository;
import com.example.catalog_microservice.repository.TrackArtistRepository;
import com.example.catalog_microservice.repository.TrackRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final TrackArtistRepository trackArtistRepository;
    private final AlbumRepository albumRepository;

    public TrackService(TrackRepository trackRepository,
                        TrackArtistRepository trackArtistRepository,
                        AlbumRepository albumRepository) {
        this.trackRepository = trackRepository;
        this.trackArtistRepository = trackArtistRepository;
        this.albumRepository = albumRepository;
    }

    public List<TrackResponseDTO> searchTracksByName(String name) {
        return trackRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public List<TrackResponseDTO> getAllTracks() {
        return trackRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TrackWithCoverDTO> getAllTracksWithCovers() {
        return trackRepository.findAll().stream()
                .map(this::convertToDTOWithCover)
                .collect(Collectors.toList());
    }

    public TrackResponseDTO getTrackById(Long id) {
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Трек не найден"));
        return convertToDTO(track);
    }

    public Track createTrackFromDTO(TrackRequestDTO dto) {
        Track track = new Track();
        track.setName(dto.getName());
        track.setDuration(dto.getDuration());
        track.setFileUrl(dto.getFileUrl());
        track.setAlbumId(dto.getAlbumId());
        track.setGenreId(dto.getGenreId());
        track.setMediaTypeId(dto.getMediaTypeId());
        validateTrack(track);
        return trackRepository.save(track);
    }

    public Track updateTrack(Long id, Track track) {
        Track existingTrack = trackRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Трек не найден"));
        validateTrack(track);
        existingTrack.setName(track.getName());
        existingTrack.setDuration(track.getDuration());
        existingTrack.setFileUrl(track.getFileUrl());
        existingTrack.setAlbumId(track.getAlbumId());
        existingTrack.setGenreId(track.getGenreId());
        existingTrack.setMediaTypeId(track.getMediaTypeId());
        return trackRepository.save(existingTrack);
    }

    public void deleteTrack(Long id) {
        if (!trackRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Трек с указанным ID не существует");
        }
        trackRepository.deleteById(id);
    }

    private void validateTrack(Track track) {
        if (track.getMediaTypeId() == null || track.getMediaTypeId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный mediaTypeId");
        }
        if (track.getAlbumId() != null && track.getAlbumId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный albumId");
        }
        if (track.getGenreId() != null && track.getGenreId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный genreId");
        }
    }

    public TrackResponseDTO convertToDTO(Track track) {
        String artistName = trackArtistRepository.findByTrack(track).stream()
                .map(TrackArtist::getArtist)
                .map(Artist::getName)
                .findFirst()
                .orElse("Неизвестный исполнитель");

        return new TrackResponseDTO(
                track.getId(),
                track.getName(),
                track.getDuration(),
                artistName,
                track.getFileUrl(),
                track.getAlbumId(),
                track.getGenreId(),
                track.getMediaTypeId()
        );
    }
    public List<Track> getTracksByGenreIds(List<Long> genreIds) {
        return trackRepository.findByGenreIdIn(genreIds);
    }

    public TrackWithCoverDTO convertToDTOWithCover(Track track) {
        TrackResponseDTO base = convertToDTO(track);
        String coverImage = albumRepository.findById(track.getAlbumId())
                .map(Album::getCoverImage)
                .orElse("default_cover.jpg");

        return new TrackWithCoverDTO(
                base.getId(),
                base.getName(),
                base.getDuration(),
                base.getArtistName(),
                base.getFileUrl(),
                base.getAlbumId(),
                base.getGenreId(),
                base.getMediaTypeId(),
                coverImage
        );
    }
}
