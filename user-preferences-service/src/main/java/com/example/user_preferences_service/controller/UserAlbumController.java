package com.example.user_preferences_service.controller;

import com.example.user_preferences_service.dto.UserAlbumDTO;
import com.example.user_preferences_service.model.UserAlbum;
import com.example.user_preferences_service.service.UserAlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/preferences/user-albums")
@RequiredArgsConstructor
@Validated
public class UserAlbumController {

    private final UserAlbumService userAlbumService;

    @PostMapping
    public ResponseEntity<UserAlbum> addUserAlbum(@Valid @RequestBody UserAlbumDTO dto) {
        UserAlbum userAlbum = userAlbumService.addUserAlbum(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAlbum);
    }

    @GetMapping("/{userId}/{albumId}")
    public ResponseEntity<UserAlbum> getUserAlbum(@PathVariable Long userId, @PathVariable Long albumId) {
        return userAlbumService.getUserAlbum(userId, albumId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}/{albumId}")
    public ResponseEntity<Void> deleteUserAlbum(@PathVariable Long userId, @PathVariable Long albumId) {
        userAlbumService.deleteUserAlbum(userId, albumId);
        return ResponseEntity.noContent().build();
    }
}
