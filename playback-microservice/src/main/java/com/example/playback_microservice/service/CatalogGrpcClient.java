package com.example.playback_microservice.service;

import com.example.grpccommon.catalog.GetTrackFilePathResponse;
import com.example.grpccommon.catalog.GetTrackRequest;
import com.example.grpccommon.catalog.TrackQueryServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogGrpcClient {

    @GrpcClient("catalog-service")
    private TrackQueryServiceGrpc.TrackQueryServiceBlockingStub catalogStub;

    public String getTrackPath(Long trackId) {
        GetTrackRequest request = GetTrackRequest.newBuilder()
                .setTrackId(trackId)
                .build();

        GetTrackFilePathResponse response = catalogStub.getTrackFilePath(request);
        return response.getFilePath();
    }
}
