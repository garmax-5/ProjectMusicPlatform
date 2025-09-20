package com.example.user_microservice.client;

import com.example.user_microservice.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import com.example.user_microservice.dto.TrackWithCoverDTO;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CatalogClient {

    private final RestTemplate restTemplate;

    @Value("${catalog.microservice.url}")
    private String catalogServiceUrl;

    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // --- Треки ---
    public List<TrackResponseDTO> searchTracksByName(String name) {
        String url = catalogServiceUrl + "/api/catalog/tracks/search?name=" +
                UriUtils.encodeQueryParam(name, StandardCharsets.UTF_8);
        TrackResponseDTO[] results = restTemplate.getForObject(url, TrackResponseDTO[].class);
        return results != null ? Arrays.asList(results) : new ArrayList<>();
    }

    public List<TrackResponseDTO> getAllTracks() {
        String url = catalogServiceUrl + "/api/catalog/tracks";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrackResponseDTO>>() {}
        ).getBody();
    }

    public TrackResponseDTO createTrack(TrackCreateDTO dto) {
        String url = catalogServiceUrl + "/api/catalog/tracks";
        return restTemplate.postForObject(url, dto, TrackResponseDTO.class);
    }

    public void deleteTrack(Long id) {
        String url = catalogServiceUrl + "/api/catalog/tracks/" + id;
        restTemplate.delete(url);
    }

    public void addTrackArtist(Long trackId, Long artistId) {
        String url = catalogServiceUrl + "/api/catalog/tracks/" + trackId + "/artists/" + artistId;
        restTemplate.postForEntity(url, null, Void.class);
    }

    // --- Альбомы ---
    public AlbumResponseDTO createAlbum(AlbumCreateDTO dto) {
        String url = catalogServiceUrl + "/api/catalog/albums";
        return restTemplate.postForObject(url, dto, AlbumResponseDTO.class);
    }

    public void deleteAlbum(Long id) {
        String url = catalogServiceUrl + "/api/catalog/albums/" + id;
        restTemplate.delete(url);
    }

    public List<AlbumResponseDTO> getAllAlbums() {
        String url = catalogServiceUrl + "/api/catalog/albums";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlbumResponseDTO>>() {}
        ).getBody();
    }

    public List<AlbumTypeDTO> getAllAlbumTypes() {
        String url = catalogServiceUrl + "/api/catalog/album-types";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlbumTypeDTO>>() {}
        ).getBody();
    }

    public AlbumDisplayDTO getAlbumById(Long albumId) {
        String url = catalogServiceUrl + "/api/catalog/albums/display/" + albumId;
        return restTemplate.getForObject(url, AlbumDisplayDTO.class);
    }

    public void addAlbumArtist(Long albumId, Long artistId) {
        String url = catalogServiceUrl + "/api/catalog/albums/" + albumId + "/artists/" + artistId;
        restTemplate.postForEntity(url, null, Void.class);
    }

    public List<AlbumResponseDTO> getAlbumsByArtist(Long artistId) {
        String url = catalogServiceUrl + "/api/catalog/albums/artists/" + artistId + "/albums";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlbumResponseDTO>>() {}
        ).getBody();
    }

    // --- Жанры / Медиа ---
    public List<GenreDTO> getAllGenres() {
        String url = catalogServiceUrl + "/api/genres";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GenreDTO>>() {}
        ).getBody();
    }

    public List<TrackResponseDTO> getTracksByGenres(List<Long> genreIds) {
        String url = catalogServiceUrl + "/api/catalog/tracks/filter-by-genres";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(genreIds, headers);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<TrackResponseDTO>>() {}
        ).getBody();
    }


    public List<MediaTypeDTO> getAllMediaTypes() {
        String url = catalogServiceUrl + "/api/catalog/media-types";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MediaTypeDTO>>() {}
        ).getBody();
    }

    // --- Артисты ---
    public List<ArtistResponseDTO> getAllArtists() {
        String url = catalogServiceUrl + "/api/catalog/artists";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ArtistResponseDTO>>() {}
        ).getBody();
    }

    public ArtistResponseDTO createArtist(ArtistRequestDTO dto) {
        String url = catalogServiceUrl + "/api/catalog/artists";
        return restTemplate.postForObject(url, dto, ArtistResponseDTO.class);
    }

    public void deleteArtist(Long artistId) {
        String url = catalogServiceUrl + "/api/catalog/artists/" + artistId;
        restTemplate.delete(url);
    }

    public ArtistProfileDTO getArtistProfile(Long artistId) {
        String url = catalogServiceUrl + "/api/catalog/artists/" + artistId + "/profile";
        return restTemplate.getForObject(url, ArtistProfileDTO.class);
    }

    public Long findArtistIdByName(String name) {
        String url = catalogServiceUrl + "/api/catalog/artists/search?name=" + name;
        return restTemplate.getForObject(url, Long.class);
    }

    // Получить все треки с обложками
    public List<TrackWithCoverDTO> getAllTracksWithCovers() {
        String url = catalogServiceUrl + "/api/catalog/tracks/with-covers";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrackWithCoverDTO>>() {}
        ).getBody();
    }

    // Получить треки по жанрам с обложками (если этот эндпоинт уже реализован в Catalog-Microservice)
    public List<TrackWithCoverDTO> getTracksWithCoversByGenres(List<Long> genreIds) {
        String url = catalogServiceUrl + "/api/catalog/tracks/filter-by-genres-with-covers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(genreIds, headers);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<TrackWithCoverDTO>>() {}
        ).getBody();
    }

    public List<AlbumResponseDTO> searchAlbumsByName(String name) {
        String url = catalogServiceUrl + "/api/catalog/albums/search?name=" +
                UriUtils.encodeQueryParam(name, StandardCharsets.UTF_8);
        AlbumResponseDTO[] results = restTemplate.getForObject(url, AlbumResponseDTO[].class);
        return results != null ? Arrays.asList(results) : new ArrayList<>();
    }

    public List<ArtistResponseDTO> searchArtistsByName(String name) {
        String url = UriComponentsBuilder
                .fromHttpUrl(catalogServiceUrl + "/api/catalog/artists/filter")
                .queryParam("name", name)
                .build()
                .toUriString();

        ArtistResponseDTO[] results = restTemplate.getForObject(url, ArtistResponseDTO[].class);
        return results != null ? Arrays.asList(results) : new ArrayList<>();
    }
}
