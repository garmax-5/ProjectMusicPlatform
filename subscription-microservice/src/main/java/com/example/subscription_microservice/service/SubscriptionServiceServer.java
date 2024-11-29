package com.example.subscription_microservice.service;

import com.example.grpccommon.CreateSubscriptionRequest;
import com.example.grpccommon.CreateSubscriptionResponse;
import com.example.grpccommon.SubscriptionServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Slf4j
@GrpcService
public class SubscriptionServiceServer extends SubscriptionServiceGrpc.SubscriptionServiceImplBase{
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
}
