package com.example.subscription_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.subscription_microservice.model.UserSubscription;

import java.time.LocalDate;
import java.util.List;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {

    // Поиск по user_id
    List<UserSubscription> findByUserId(Long userId);

    // Поиск по subscription_id
    List<UserSubscription> findBySubscription_SubscriptionId(Long subscriptionId);

    // Найти по user_subscription_id
    UserSubscription findByUserSubscriptionId(Long userSubscriptionId);

    @Modifying
    @Query("UPDATE UserSubscription us SET us.isActive = false WHERE us.isActive = true AND us.subscriptionEndDate <= :currentDate")
    void deactivateExpiredSubscriptions(@Param("currentDate") LocalDate currentDate);
}
