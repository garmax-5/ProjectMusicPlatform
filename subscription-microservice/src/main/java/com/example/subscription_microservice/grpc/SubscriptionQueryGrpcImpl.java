package com.example.subscription_microservice.grpc;

import com.example.grpccommon.query.SubscriptionQueryServiceGrpc;
import com.example.grpccommon.query.SubscriptionCheckRequest;
import com.example.grpccommon.query.SubscriptionCheckResponse;
import com.example.subscription_microservice.repository.UserSubscriptionRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class SubscriptionQueryGrpcImpl extends SubscriptionQueryServiceGrpc.SubscriptionQueryServiceImplBase {

    private final UserSubscriptionRepository userSubscriptionRepository;

    @Override
    public void hasActiveSubscription(SubscriptionCheckRequest request, StreamObserver<SubscriptionCheckResponse> responseObserver) {
        Long userId = request.getUserId();
        log.info("Получен запрос на проверку подписки для пользователя ID: {}", userId);

        boolean isActive = userSubscriptionRepository
                .findByUserId(userId)
                .stream()
                .anyMatch(sub -> Boolean.TRUE.equals(sub.getIsActive()));

        SubscriptionCheckResponse response = SubscriptionCheckResponse.newBuilder()
                .setIsActive(isActive)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
