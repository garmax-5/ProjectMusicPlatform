package com.example.catalog_microservice.grpc;

import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.repository.TrackRepository;
import com.example.grpccommon.catalog.GetTrackFilePathResponse;
import com.example.grpccommon.catalog.GetTrackRequest;
import com.example.grpccommon.catalog.TrackQueryServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class TrackQueryGrpcImpl extends TrackQueryServiceGrpc.TrackQueryServiceImplBase {

    private final TrackRepository trackRepository;

    @Override
    public void getTrackFilePath(GetTrackRequest request, StreamObserver<GetTrackFilePathResponse> responseObserver) {
        Long trackId = request.getTrackId();
        log.info("gRPC-запрос: получение пути к файлу трека с ID {}", trackId);

        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Трек не найден"));

        GetTrackFilePathResponse response = GetTrackFilePathResponse.newBuilder()
                .setFilePath(track.getFileUrl())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
