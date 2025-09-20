package com.example.user_preferences_service.controller;

import com.example.user_preferences_service.dto.UserArtistDTO;
import com.example.user_preferences_service.model.UserArtist;
import com.example.user_preferences_service.service.UserArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/preferences/user-artists")
@RequiredArgsConstructor
@Validated
public class UserArtistController {

    private final UserArtistService userArtistService;

    @PostMapping
    public ResponseEntity<UserArtist> addUserArtist(@Valid @RequestBody UserArtistDTO dto) {
        UserArtist userArtist = userArtistService.addUserArtist(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userArtist);
    }

    @GetMapping("/{userId}/{artistId}")
    public ResponseEntity<UserArtist> getUserArtist(@PathVariable Long userId, @PathVariable Long artistId) {
        return userArtistService.getUserArtist(userId, artistId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}/{artistId}")
    public ResponseEntity<Void> deleteUserArtist(@PathVariable Long userId, @PathVariable Long artistId) {
        userArtistService.deleteUserArtist(userId, artistId);
        return ResponseEntity.noContent().build();
    }
}
