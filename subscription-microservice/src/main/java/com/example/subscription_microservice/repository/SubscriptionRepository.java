package com.example.subscription_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.subscription_microservice.model.Subscription;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    // Поиск подписки по её идентификатору
    Optional<Subscription> findById(Long subscriptionId);

//    Optional<Subscription> findByUserIdAndSubscriptionId(Long userId, Long subscriptionId);

}

