package com.example.user_preferences_service.service;

import com.example.user_preferences_service.dto.UserArtistDTO;
import com.example.user_preferences_service.model.UserArtist;
import com.example.user_preferences_service.repository.UserArtistRepository;
import com.example.grpccommon.CatalogServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class UserArtistService {

    private final UserArtistRepository userArtistRepository;
    private final CatalogClient catalogClient;

    public UserArtist addUserArtist(UserArtistDTO userArtistDTO) {
        if (userArtistDTO.getUserId() == null || userArtistDTO.getArtistId() == null) {
            throw new IllegalArgumentException("Необходимо указать идентификатор пользователя и исполнителя");
        }

        if (!catalogClient.checkArtistExists(userArtistDTO.getArtistId())) {
            throw new IllegalArgumentException("Исполнитель с ID " + userArtistDTO.getArtistId() + " не существует");
        }

        var id = new UserArtist.UserArtistId(userArtistDTO.getUserId(), userArtistDTO.getArtistId());
        return userArtistRepository.save(new UserArtist(id));
    }

    public Optional<UserArtist> getUserArtist(Long userId, Long artistId) {
        return userArtistRepository.findById(new UserArtist.UserArtistId(userId, artistId));
    }

    public void deleteUserArtist(Long userId, Long artistId) {
        userArtistRepository.deleteById(new UserArtist.UserArtistId(userId, artistId));
    }
}
