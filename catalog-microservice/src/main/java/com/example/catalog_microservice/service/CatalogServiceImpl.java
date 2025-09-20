package com.example.catalog_microservice.service;

import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.repository.AlbumRepository;
import com.example.catalog_microservice.repository.ArtistRepository;
import com.example.catalog_microservice.repository.TrackArtistRepository;
import com.example.catalog_microservice.repository.TrackRepository;
import com.example.grpccommon.*;
import com.example.grpccommon.catalog.GetTrackRequest;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class CatalogServiceImpl extends CatalogServiceGrpc.CatalogServiceImplBase {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;
    private final TrackArtistRepository trackArtistRepository;


    @Override
    public void checkAlbumExists(CheckAlbumRequest request, StreamObserver<ExistsResponse> responseObserver) {
        boolean exists = albumRepository.existsById(request.getAlbumId());
        log.info("Checking existence of album with ID {}: {}", request.getAlbumId(), exists);
        responseObserver.onNext(ExistsResponse.newBuilder().setExists(exists).build());
        responseObserver.onCompleted();
    }

    @Override
    public void checkArtistExists(CheckArtistRequest request, StreamObserver<ExistsResponse> responseObserver) {
        boolean exists = artistRepository.existsById(request.getArtistId());
        log.info("Checking existence of artist with ID {}: {}", request.getArtistId(), exists);
        responseObserver.onNext(ExistsResponse.newBuilder().setExists(exists).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getTrackById(CheckTrackRequest request, StreamObserver<TrackResponse> responseObserver) {
        Track track = trackRepository.findById(request.getTrackId())
                .orElseThrow(() -> new RuntimeException("Track not found"));

        String artistName = trackArtistRepository.findFirstByTrackId(track.getId())
                .map(ta -> ta.getArtist().getName())
                .orElse("Неизвестный");

        TrackResponse response = TrackResponse.newBuilder()
                .setTrackId(track.getId())
                .setTrackName(track.getName())
                .setFileUrl(track.getFileUrl())
                .setArtistName(artistName)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}

