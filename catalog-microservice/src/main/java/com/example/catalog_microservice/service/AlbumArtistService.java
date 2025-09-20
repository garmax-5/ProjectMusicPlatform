package com.example.catalog_microservice.service;

import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.model.AlbumArtist;
import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.repository.AlbumArtistRepository;
import com.example.catalog_microservice.repository.AlbumRepository;
import com.example.catalog_microservice.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumArtistService {

    private final AlbumArtistRepository albumArtistRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public void addAlbumArtist(Long albumId, Long artistId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Альбом не найден"));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Артист не найден"));

        AlbumArtist albumArtist = new AlbumArtist(new AlbumArtist.AlbumArtistId(albumId, artistId), album, artist);
        albumArtistRepository.save(albumArtist);
    }

    public void removeAlbumArtist(Long albumId, Long artistId) {
        AlbumArtist.AlbumArtistId id = new AlbumArtist.AlbumArtistId(albumId, artistId);
        if (!albumArtistRepository.existsById(id)) {
            throw new EntityNotFoundException("Связь альбома и артиста не найдена");
        }
        albumArtistRepository.deleteById(id);
    }

    public List<Artist> getArtistsByAlbum(Long albumId) {
        return albumArtistRepository.findArtistsByAlbumId(albumId);
    }

    public List<Album> getAlbumsByArtist(Long artistId) {
        return albumArtistRepository.findAlbumsByArtistId(artistId);
    }
}

