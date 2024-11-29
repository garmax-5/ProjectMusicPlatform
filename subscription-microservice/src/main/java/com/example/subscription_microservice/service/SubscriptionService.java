package com.example.subscription_microservice.service;

import com.example.subscription_microservice.model.Subscription;
import com.example.subscription_microservice.model.UserSubscription;

import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);

    List<Subscription> findSubscriptionsByUserId(Long userId);

    UserSubscription addSubscriptionToUser(Long userId, Long subscriptionId);
}
