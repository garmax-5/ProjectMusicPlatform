package com.example.user_preferences_service.service;

import com.example.grpccommon.*;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogClient {

    @GrpcClient("catalog-service")
    private CatalogServiceGrpc.CatalogServiceBlockingStub catalogStub;

    public boolean checkAlbumExists(Long albumId) {
        try {
            log.info("Проверка существования альбома с ID: {}", albumId);
            ExistsResponse response = catalogStub.checkAlbumExists(
                    CheckAlbumRequest.newBuilder().setAlbumId(albumId).build()
            );
            log.info("Альбом с ID {} {} существует", albumId, response.getExists() ? "" : "не");
            return response.getExists();
        } catch (StatusRuntimeException e) {
            log.error("gRPC ошибка при проверке альбома с ID {}: {}", albumId, e.getStatus());
            throw new IllegalStateException("Каталог недоступен для проверки альбома", e);
        }
    }

    public boolean checkArtistExists(Long artistId) {
        try {
            log.info("Проверка существования артиста с ID: {}", artistId);
            ExistsResponse response = catalogStub.checkArtistExists(
                    CheckArtistRequest.newBuilder().setArtistId(artistId).build()
            );
            log.info("Артист с ID {} {} существует", artistId, response.getExists() ? "" : "не");
            return response.getExists();
        } catch (StatusRuntimeException e) {
            log.error("gRPC ошибка при проверке артиста с ID {}: {}", artistId, e.getStatus());
            throw new IllegalStateException("Каталог недоступен для проверки артиста", e);
        }
    }

    public TrackResponse getTrackById(Long trackId) {
        try {
            log.info("Получение трека с ID: {}", trackId);
            return catalogStub.getTrackById(
                    CheckTrackRequest.newBuilder().setTrackId(trackId).build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC ошибка при получении трека с ID {}: {}", trackId, e.getStatus());
            throw new IllegalStateException("Каталог недоступен для получения трека", e);
        }
    }
}
