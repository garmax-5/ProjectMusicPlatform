package com.example.user_microservice.service;

import com.example.grpccommon.SubscriptionServiceGrpc;
import com.example.grpccommon.CreateSubscriptionResponse;
import com.example.grpccommon.CreateSubscriptionRequest;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubscriptionClient {
    @GrpcClient(value = "data-generator-blocking")
    private SubscriptionServiceGrpc.SubscriptionServiceBlockingStub subscriptionStub;

//    public SubscriptionClient(@Value("${grpc.client.GLOBAL.address}") String grpcServerAddress) {
//        ManagedChannel channel = ManagedChannelBuilder
//                .forTarget(grpcServerAddress)
//                .usePlaintext()
//                .build();
//        this.subscriptionStub = SubscriptionServiceGrpc.newBlockingStub(channel);
//    }

    public boolean sendUserSubscription(Long userId, Long subscriptionId) {
        // Подготовка запроса
        CreateSubscriptionRequest request = CreateSubscriptionRequest.newBuilder()
                .setUserId(userId)
                .setSubscriptionId(subscriptionId)
                .build();

        // Вызов удаленного метода
        CreateSubscriptionResponse response;
        try {
            response = subscriptionStub.createSubscription(request);
        } catch (Exception e) {
            log.error("Ошибка при вызове службы gRPC: {}", e.getMessage());
            return false;
        }

        // Обработать ответ
        if (response.getSuccess()) {
            log.info("Подписка успешно создана: {}", response.getMessage());
            return true;
        } else {
            log.warn("Не удалось создать подписку: {}", response.getMessage());
            return false;
        }
    }
}
