package com.example.user_preferences_service.service;

import com.example.user_preferences_service.dto.TrackDisplayDTO;
import com.example.user_preferences_service.model.Playlist;
import com.example.user_preferences_service.model.PlaylistTrack;
import com.example.user_preferences_service.repository.PlaylistRepository;
import com.example.user_preferences_service.repository.PlaylistTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistTrackService {

    private final PlaylistTrackRepository playlistTrackRepository;
    private final PlaylistRepository playlistRepository;
    private final CatalogClient catalogClient;

    public List<TrackDisplayDTO> getTracksInPlaylist(Long playlistId) {
        return playlistTrackRepository.findByIdPlaylistId(playlistId).stream()
                .map(pt -> {
                    var grpcTrack = catalogClient.getTrackById(pt.getId().getTrackId());
                    return new TrackDisplayDTO(
                            grpcTrack.getTrackId(),
                            grpcTrack.getTrackName(),
                            grpcTrack.getArtistName(),
                            grpcTrack.getFileUrl()
                    );
                }).collect(Collectors.toList());
    }

    public void removeTrackFromPlaylist(Long playlistId, Long trackId) {
        playlistTrackRepository.deleteById(new PlaylistTrack.PlaylistTrackId(playlistId, trackId));
    }

    public void addTrackToPlaylist(Long playlistId, Long trackId, Long userId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Плейлист не найден"));

        if (!playlist.getUserId().equals(userId)) {
            throw new SecurityException("Вы не владелец плейлиста");
        }

        if (playlistTrackRepository.existsById(new PlaylistTrack.PlaylistTrackId(playlistId, trackId))) {
            throw new IllegalStateException("Трек уже в плейлисте");
        }

        // Проверка, существует ли трек
        catalogClient.getTrackById(trackId); // выбросит исключение, если не найден

        PlaylistTrack entity = new PlaylistTrack(new PlaylistTrack.PlaylistTrackId(playlistId, trackId));
        playlistTrackRepository.save(entity);
    }

}

