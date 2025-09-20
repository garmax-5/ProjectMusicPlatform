package com.example.user_microservice.client;

import com.example.user_microservice.dto.PlaylistDTO;
import com.example.user_microservice.dto.TrackInPlaylistDTO;
import com.example.user_microservice.dto.UserPlaylistDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlaylistClient {

    private final RestTemplate restTemplate;

    @Value("${user.preferences.url}")
    private String preferencesUrl;

    // ====== Получить плейлист по ID ======
    public PlaylistDTO getPlaylistById(Long playlistId) {
        String url = preferencesUrl + "/api/preferences/playlists/by-id/" + playlistId;
        return restTemplate.getForObject(url, PlaylistDTO.class);
    }

    // ====== Получить треки в плейлисте ======
    public List<TrackInPlaylistDTO> getTracksInPlaylist(Long playlistId) {
        String url = preferencesUrl + "/api/preferences/playlists/" + playlistId + "/tracks";
        ResponseEntity<List<TrackInPlaylistDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    // ====== Удалить трек из плейлиста ======
    public void removeTrackFromPlaylist(Long playlistId, Long trackId) {
        String url = preferencesUrl + "/api/preferences/playlists/" + playlistId + "/tracks/" + trackId;
        restTemplate.delete(url);
    }

    // ====== Получить плейлисты пользователя ======
    public List<PlaylistDTO> getUserPlaylists(Long userId) {
        String url = preferencesUrl + "/api/preferences/playlists/user/" + userId;
        ResponseEntity<List<PlaylistDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    // ====== Создать плейлист ======
    public PlaylistDTO createPlaylist(PlaylistDTO dto) {
        String url = preferencesUrl + "/api/preferences/playlists";
        return restTemplate.postForObject(url, dto, PlaylistDTO.class);
    }

    // ====== Привязать плейлист к пользователю ======
    public void linkPlaylistToUser(Long userId, Long playlistId) {
        String url = preferencesUrl + "/api/user-playlists";
        UserPlaylistDTO dto = new UserPlaylistDTO(userId, playlistId);
        restTemplate.postForEntity(url, dto, Void.class);
    }

    // ====== Добавить трек в плейлист ======
    public void addTrackToPlaylist(Long playlistId, Long trackId, Long userId) {
        String url = preferencesUrl + "/api/preferences/playlists/" + playlistId + "/tracks";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Long> requestBody = Map.of(
                "playlistId", playlistId,
                "trackId", trackId,
                "userId", userId
        );

        HttpEntity<Map<String, Long>> request = new HttpEntity<>(requestBody, headers);
        restTemplate.postForEntity(url, request, Void.class);
    }

    // ====== Отвязать плейлист от пользователя ======
    public void unlinkPlaylistFromUser(Long userId, Long playlistId) {
        String url = preferencesUrl + "/api/user-playlists/" + userId + "/" + playlistId;
        restTemplate.delete(url);
    }

    // ====== Удалить плейлист ======
    public void deletePlaylist(Long playlistId) {
        String url = preferencesUrl + "/api/preferences/playlists/" + playlistId;
        restTemplate.delete(url);
    }

    // ====== Получить или создать системный плейлист по имени ======
    public PlaylistDTO getOrCreateSystemPlaylist(String name, Long userId) {
        List<PlaylistDTO> playlists = getUserPlaylists(userId);

        for (PlaylistDTO playlist : playlists) {
            if (playlist.isSystem() && playlist.getName().equalsIgnoreCase(name)) {
                return playlist;
            }
        }

        PlaylistDTO newPlaylist = new PlaylistDTO();
        newPlaylist.setName(name);
        newPlaylist.setSystem(true);
        newPlaylist.setUserId(userId);

        PlaylistDTO created = createPlaylist(newPlaylist);
        linkPlaylistToUser(userId, created.getId());

        return created;
    }
}
