package com.example.subscription_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {
    private Long userId;          // ID пользователя, который оформляет подписку
    private Long subscriptionId;  // ID подписки, которую пользователь хочет оформить
}