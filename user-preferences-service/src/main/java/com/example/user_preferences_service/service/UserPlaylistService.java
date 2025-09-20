package com.example.user_preferences_service.service;

import com.example.user_preferences_service.dto.UserPlaylistDTO;
import com.example.user_preferences_service.model.UserPlaylist;
import com.example.user_preferences_service.repository.UserPlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class UserPlaylistService {

    private final UserPlaylistRepository userPlaylistRepository;

    public void addUserToPlaylist(UserPlaylistDTO dto) {
        userPlaylistRepository.save(new UserPlaylist(
                new UserPlaylist.UserPlaylistId(dto.getUserId(), dto.getPlaylistId())
        ));
    }

    public List<UserPlaylistDTO> getUserPlaylists(Long userId) {
        return userPlaylistRepository.findByIdUserId(userId).stream()
                .map(up -> new UserPlaylistDTO(up.getId().getUserId(), up.getId().getPlaylistId()))
                .collect(Collectors.toList());
    }

    public void removeUserFromPlaylist(Long userId, Long playlistId) {
        userPlaylistRepository.deleteById(new UserPlaylist.UserPlaylistId(userId, playlistId));
    }
}

