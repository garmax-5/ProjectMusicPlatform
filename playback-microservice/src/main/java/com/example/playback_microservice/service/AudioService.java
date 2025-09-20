package com.example.playback_microservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
@Slf4j
public class AudioService {

    private static final int PREVIEW_SECONDS = 30;
    private static final int AVERAGE_BITRATE_KBPS = 128;
    private static final int BYTES_PER_SECOND = (AVERAGE_BITRATE_KBPS * 1000) / 8;

    public byte[] readAudio(String path, boolean fullAccess) {
        try {
            Path audioPath = Paths.get(path);
            if (!Files.exists(audioPath)) {
                log.error("Файл не найден: {}", path);
                throw new IllegalArgumentException("Аудиофайл не найден: " + path);
            }

            byte[] allBytes = Files.readAllBytes(audioPath);
            if (fullAccess) {
                log.debug("Полный доступ к аудио");
                return allBytes;
            }

            int previewLength = Math.min(allBytes.length, PREVIEW_SECONDS * BYTES_PER_SECOND);
            log.debug("Доступ только к превью, {} секунд", PREVIEW_SECONDS);
            return Arrays.copyOf(allBytes, previewLength);

        } catch (IOException e) {
            log.error("Ошибка при чтении файла: {}", path, e);
            throw new RuntimeException("Ошибка при чтении аудиофайла: " + path, e);
        }
    }
}


