package com.example.playback_microservice.grpc;

import com.example.grpccommon.query.SubscriptionCheckRequest;
import com.example.grpccommon.query.SubscriptionCheckResponse;
import com.example.grpccommon.query.SubscriptionQueryServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionGrpcClient {

    @GrpcClient("subscription-service")
    private SubscriptionQueryServiceGrpc.SubscriptionQueryServiceBlockingStub subscriptionStub;

    public boolean hasActiveSubscription(Long userId) {
        try {
            log.info("Выполняется проверка подписки для пользователя ID {}", userId);

            SubscriptionCheckRequest request = SubscriptionCheckRequest.newBuilder()
                    .setUserId(userId)
                    .build();

            SubscriptionCheckResponse response = subscriptionStub.hasActiveSubscription(request);
            boolean active = response.getIsActive();

            log.info("Результат подписки для пользователя ID {}: {}", userId, active);
            return active;
        } catch (Exception e) {
            log.error("Ошибка при gRPC-запросе к SubscriptionQueryService: {}", e.getMessage(), e);
            throw new IllegalStateException("Не удалось проверить подписку", e);
        }
    }
}
