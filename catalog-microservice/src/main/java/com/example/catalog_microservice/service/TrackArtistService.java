package com.example.catalog_microservice.service;


import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.model.TrackArtist;
import com.example.catalog_microservice.repository.ArtistRepository;
import com.example.catalog_microservice.repository.TrackArtistRepository;
import com.example.catalog_microservice.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackArtistService {

    private final TrackArtistRepository trackArtistRepository;
    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;

    public void addTrackArtist(Long trackId, Long artistId) {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new EntityNotFoundException("Трек не найден"));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Артист не найден"));

        TrackArtist trackArtist = new TrackArtist(new TrackArtist.TrackArtistId(trackId, artistId), track, artist);
        trackArtistRepository.save(trackArtist);
    }

    public void removeTrackArtist(Long trackId, Long artistId) {
        TrackArtist.TrackArtistId id = new TrackArtist.TrackArtistId(trackId, artistId);
        if (!trackArtistRepository.existsById(id)) {
            throw new EntityNotFoundException("Связь трека и артиста не найдена");
        }
        trackArtistRepository.deleteById(id);
    }

    public List<Artist> getArtistsByTrack(Long trackId) {
        return trackArtistRepository.findArtistsByTrackId(trackId);
    }

    public List<Track> getTracksByArtist(Long artistId) {
        return trackArtistRepository.findTracksByArtistId(artistId);
    }
}

