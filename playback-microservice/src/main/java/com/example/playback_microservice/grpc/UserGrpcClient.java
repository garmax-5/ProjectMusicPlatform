package com.example.playback_microservice.grpc;

import com.example.grpccommon.user.UserQueryServiceGrpc;
import com.example.grpccommon.user.UserRequest;
import com.example.grpccommon.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserGrpcClient {

    @GrpcClient("user-service")
    private UserQueryServiceGrpc.UserQueryServiceBlockingStub userStub;

    public Long getUserId(String username) {
        try {
            log.info("Получение userId по username: {}", username);
            UserRequest request = UserRequest.newBuilder()
                    .setUsername(username)
                    .build();

            UserResponse response = userStub.getUserIdByUsername(request);
            return response.getUserId();
        } catch (Exception e) {
            log.error("Ошибка при получении userId: {}", e.getMessage(), e);
            throw new IllegalStateException("Не удалось получить userId по username", e);
        }
    }
}
