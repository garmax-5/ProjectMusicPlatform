package com.example.catalog_microservice.service;

import com.example.catalog_microservice.dto.AlbumRequestDTO;
import com.example.catalog_microservice.dto.AlbumResponseDTO;
import com.example.catalog_microservice.dto.TrackDisplayDTO;
import com.example.catalog_microservice.dto.AlbumDisplayDTO;
import com.example.catalog_microservice.dto.TrackDisplayDTO;
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
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;
    private final TrackArtistRepository trackArtistRepository;
    private final AlbumArtistRepository albumArtistRepository;


    public AlbumService(AlbumRepository albumRepository, TrackRepository trackRepository, TrackArtistRepository trackArtistRepository, AlbumArtistRepository albumArtistRepository) {
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;

        this.trackArtistRepository = trackArtistRepository;
        this.albumArtistRepository = albumArtistRepository;
    }

    public List<AlbumResponseDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AlbumResponseDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Альбом не найден"));
        return convertToDTO(album);
    }

    public AlbumResponseDTO createAlbumFromDTO(AlbumRequestDTO dto) {
        Album album = new Album();
        album.setAlbumName(dto.getAlbumName());
        album.setReleaseYear(dto.getReleaseYear());
        album.setAlbumTypeId(dto.getAlbumTypeId());
        album.setCoverImage(dto.getCoverImage());
        validateAlbum(album);
        return convertToDTO(albumRepository.save(album));
    }

    public AlbumResponseDTO updateAlbum(Long id, Album album) {
        Album existingAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Альбом не найден"));

        validateAlbum(album);

        existingAlbum.setAlbumName(album.getAlbumName());
        existingAlbum.setReleaseYear(album.getReleaseYear());
        existingAlbum.setAlbumTypeId(album.getAlbumTypeId());
        existingAlbum.setCoverImage(album.getCoverImage());

        return convertToDTO(albumRepository.save(existingAlbum));
    }

    public void deleteAlbum(Long id) {
        if (!albumRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Альбом с указанным ID не существует");
        }
        albumRepository.deleteById(id);
    }

    private void validateAlbum(Album album) {
        if (album.getAlbumName() == null || album.getAlbumName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Название альбома не может быть пустым");
        }
        if (album.getReleaseYear() == null || album.getReleaseYear() < 1800) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный год выхода альбома");
        }
        if (album.getAlbumTypeId() == null || album.getAlbumTypeId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный albumTypeId");
        }
    }

    public AlbumResponseDTO convertToDTO(Album album) {
        return new AlbumResponseDTO(
                album.getId(),
                album.getAlbumName(),
                album.getReleaseYear(),
                album.getAlbumTypeId(),
                album.getCoverImage()
        );
    }

    public AlbumDisplayDTO getAlbumDisplayById(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Альбом не найден"));

        AlbumArtist albumArtist = albumArtistRepository.findByAlbumId(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Исполнитель альбома не найден"));
        Artist artist = albumArtist.getArtist();

        List<Track> tracks = trackRepository.findByAlbumId(albumId);

        List<TrackDisplayDTO> trackDTOs = tracks.stream().map(track -> {
            String artistName = trackArtistRepository.findByTrackId(track.getId())
                    .map(ta -> ta.getArtist().getName())
                    .orElse("Неизвестный артист");

            return new TrackDisplayDTO(
                    track.getId(),
                    track.getName(),
                    artistName,
                    track.getFileUrl()
            );
        }).collect(Collectors.toList());

        return new AlbumDisplayDTO(
                album.getId(),
                album.getAlbumName(),
                album.getCoverImage(),
                album.getReleaseYear(),
                artist.getName(),
                trackDTOs
        );
    }

    public List<AlbumResponseDTO> searchAlbumsByName(String name) {
        return albumRepository.findByAlbumNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

