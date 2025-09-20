package com.example.user_microservice.service;

import com.example.grpccommon.*;

import com.example.user_microservice.dto.SubscriptionResponseDTO;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubscriptionClient {
    @GrpcClient(value = "data-generator-blocking")
    private SubscriptionServiceGrpc.SubscriptionServiceBlockingStub subscriptionStub;

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

    public List<SubscriptionResponseDTO> getAllAvailableSubscriptions() {
        try {
            SubscriptionListResponse response = subscriptionStub.getAvailableSubscriptions(Empty.newBuilder().build());
            return response.getSubscriptionsList().stream()
                    .map(s -> new SubscriptionResponseDTO(
                            s.getSubscriptionId(),
                            s.getSubscriptionName(),
                            s.getPrice(),
                            s.getDurationDays()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Ошибка при получении подписок: {}", e.getMessage());
            return List.of();
        }
    }

    public List<SubscriptionResponseDTO> getUserSubscriptions(Long userId) {
        try {
            UserIdRequest request = UserIdRequest.newBuilder().setUserId(userId).build();
            SubscriptionListResponse response = subscriptionStub.getSubscriptionsByUserId(request);
            return response.getSubscriptionsList().stream()
                    .map(s -> new SubscriptionResponseDTO(
                            s.getSubscriptionId(),
                            s.getSubscriptionName(),
                            s.getPrice(),
                            s.getDurationDays()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Ошибка при получении подписок пользователя: {}", e.getMessage());
            return List.of();
        }
    }
}
