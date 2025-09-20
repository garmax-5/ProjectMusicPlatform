package com.example.subscription_microservice.service;

import com.example.grpccommon.*;
import com.example.subscription_microservice.model.Subscription;
import com.example.subscription_microservice.service.SubscriptionService;
import com.example.grpccommon.Empty;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@Slf4j
@GrpcService
public class SubscriptionServiceServer extends SubscriptionServiceGrpc.SubscriptionServiceImplBase {

    private final SubscriptionService subscriptionService;

    public SubscriptionServiceServer(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void createSubscription(CreateSubscriptionRequest request, StreamObserver<CreateSubscriptionResponse> responseObserver) {
        Long userId = request.getUserId();
        Long subscriptionId = request.getSubscriptionId();

        log.info("Запрос на создание подписки: userId={}, subscriptionId={}", userId, subscriptionId);

        try {
            subscriptionService.addSubscriptionToUser(userId, subscriptionId);

            CreateSubscriptionResponse response = CreateSubscriptionResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Подписка успешно создана!")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Ошибка при создании подписки: {}", e.getMessage());

            CreateSubscriptionResponse response = CreateSubscriptionResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Ошибка: " + e.getMessage())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getAvailableSubscriptions(Empty request, StreamObserver<SubscriptionListResponse> responseObserver) {
        List<Subscription> all = subscriptionService.getAll();

        SubscriptionListResponse.Builder responseBuilder = SubscriptionListResponse.newBuilder();
        for (Subscription s : all) {
            responseBuilder.addSubscriptions(
                    com.example.grpccommon.Subscription.newBuilder()
                            .setSubscriptionId(s.getSubscriptionId())
                            .setSubscriptionName(s.getSubscriptionName())
                            .setPrice(s.getPrice())
                            .setDurationDays(s.getDurationDays())
                            .build()
            );
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSubscriptionsByUserId(UserIdRequest request, StreamObserver<SubscriptionListResponse> responseObserver) {
        long userId = request.getUserId();
        List<com.example.subscription_microservice.dto.SubscriptionResponseDTO> userSubs = subscriptionService.findSubscriptionsByUserId(userId);

        SubscriptionListResponse.Builder responseBuilder = SubscriptionListResponse.newBuilder();
        for (com.example.subscription_microservice.dto.SubscriptionResponseDTO s : userSubs) {
            responseBuilder.addSubscriptions(
                    com.example.grpccommon.Subscription.newBuilder()
                            .setSubscriptionId(s.getSubscriptionId())
                            .setSubscriptionName(s.getSubscriptionName())
                            .setPrice(s.getPrice())
                            .setDurationDays(s.getDurationDays())
                            .build()
            );
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
