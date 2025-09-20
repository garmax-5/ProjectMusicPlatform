package com.example.user_preferences_service.service;

import com.example.user_preferences_service.dto.PlaylistDTO;
import com.example.user_preferences_service.model.Playlist;
import com.example.user_preferences_service.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistDTO createPlaylist(PlaylistDTO dto) {
        Playlist playlist = new Playlist(null, dto.getName(), dto.isSystem(), dto.getUserId());
        Playlist saved = playlistRepository.save(playlist);
        return new PlaylistDTO(saved.getId(), saved.getName(), saved.isSystem(), saved.getUserId());
    }

    public List<PlaylistDTO> getPlaylistsByUser(Long userId) {
        return playlistRepository.findByUserId(userId).stream()
                .map(p -> new PlaylistDTO(p.getId(), p.getName(), p.isSystem(), p.getUserId()))
                .collect(Collectors.toList());
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    public PlaylistDTO getPlaylistById(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Плейлист не найден"));

        return convertToDTO(playlist);
    }

    private PlaylistDTO convertToDTO(Playlist playlist) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(playlist.getId());
        dto.setName(playlist.getName());
        dto.setSystem(playlist.isSystem());
        dto.setUserId(playlist.getUserId());
        return dto;
    }
}

