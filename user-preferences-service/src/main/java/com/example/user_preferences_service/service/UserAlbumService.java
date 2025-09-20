package com.example.user_preferences_service.service;

import com.example.user_preferences_service.dto.UserAlbumDTO;
import com.example.user_preferences_service.model.UserAlbum;
import com.example.user_preferences_service.repository.UserAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class UserAlbumService {

    private final UserAlbumRepository userAlbumRepository;
    private final CatalogClient catalogClient;

    public UserAlbum addUserAlbum(UserAlbumDTO dto) {
        if (!catalogClient.checkAlbumExists(dto.getAlbumId())) {
            throw new IllegalArgumentException("Альбом не найден: " + dto.getAlbumId());
        }

        var id = new UserAlbum.UserAlbumId(dto.getUserId(), dto.getAlbumId());
        return userAlbumRepository.save(new UserAlbum(id, LocalDateTime.now()));
    }

    public Optional<UserAlbum> getUserAlbum(Long userId, Long albumId) {
        return userAlbumRepository.findById(new UserAlbum.UserAlbumId(userId, albumId));
    }

    public void deleteUserAlbum(Long userId, Long albumId) {
        userAlbumRepository.deleteById(new UserAlbum.UserAlbumId(userId, albumId));
    }
}


