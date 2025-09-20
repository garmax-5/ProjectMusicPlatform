package com.example.subscription_microservice.service;

import com.example.subscription_microservice.dto.SubscriptionRequestDTO;
import com.example.subscription_microservice.dto.SubscriptionResponseDTO;
import com.example.subscription_microservice.model.Subscription;
import com.example.subscription_microservice.model.UserSubscription;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDTO createSubscription(SubscriptionRequestDTO dto);

    List<SubscriptionResponseDTO> findSubscriptionsByUserId(Long userId);

    UserSubscription addSubscriptionToUser(Long userId, Long subscriptionId);

    List<Subscription> getAll();
}
