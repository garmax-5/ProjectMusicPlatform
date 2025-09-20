package com.example.playback_microservice.controller;

import com.example.playback_microservice.grpc.SubscriptionGrpcClient;
import com.example.playback_microservice.grpc.UserGrpcClient;
import com.example.playback_microservice.service.AudioService;
import com.example.playback_microservice.service.CatalogGrpcClient;
import com.example.playback_microservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playback")
@RequiredArgsConstructor
@Slf4j
public class PlaybackController {

    private final SubscriptionGrpcClient subscriptionClient;
    private final CatalogGrpcClient catalogClient;
    private final AudioService audioService;
    private final JwtUtil jwtUtil;
    private final UserGrpcClient userGrpcClient;

    @GetMapping("/secure/{trackId}")
    public ResponseEntity<byte[]> playbackNoRestrictions(@PathVariable Long trackId) {
        try {
            if (trackId == null || trackId <= 0) {
                return ResponseEntity.badRequest().build();
            }

            String filePath = catalogClient.getTrackPath(trackId);
            byte[] audioBytes = audioService.readAudio(filePath, true); // Всегда полный трек

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("audio/mpeg"))
                    .body(audioBytes);

        } catch (IllegalArgumentException e) {
            log.warn("Некорректный ID трека или ошибка пути: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            log.error("Ошибка при воспроизведении трека {}: {}", trackId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/public/{trackId}")
    public ResponseEntity<byte[]> publicPlayback(@PathVariable Long trackId) {
        try {
            if (trackId == null || trackId <= 0) {
                return ResponseEntity.badRequest().build();
            }

            String filePath = catalogClient.getTrackPath(trackId);
            byte[] preview = audioService.readAudio(filePath, false);

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("audio/mpeg"))
                    .body(preview);

        } catch (Exception e) {
            log.error("Ошибка при публичном воспроизведении трека {}: {}", trackId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
