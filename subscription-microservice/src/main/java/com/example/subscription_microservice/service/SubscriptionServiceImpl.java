package com.example.subscription_microservice.service;

import com.example.subscription_microservice.dto.SubscriptionRequestDTO;
import com.example.subscription_microservice.dto.SubscriptionResponseDTO;
import com.example.subscription_microservice.model.Subscription;
import com.example.subscription_microservice.model.UserSubscription;
import com.example.subscription_microservice.repository.SubscriptionRepository;
import com.example.subscription_microservice.repository.UserSubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   UserSubscriptionRepository userSubscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    @Override
    public UserSubscription addSubscriptionToUser(Long userId, Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Подписка с ID " + subscriptionId + " не найдена"));

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(subscription.getDurationDays());

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUserId(userId);
        userSubscription.setSubscription(subscription);
        userSubscription.setSubscriptionStartDate(startDate);
        userSubscription.setSubscriptionEndDate(endDate);
        userSubscription.setIsActive(true);

        return userSubscriptionRepository.save(userSubscription);
    }

    @Override
    public SubscriptionResponseDTO createSubscription(SubscriptionRequestDTO dto) {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionName(dto.getSubscriptionName());
        subscription.setPrice(dto.getPrice());
        subscription.setDurationDays(dto.getDurationDays());

        Subscription saved = subscriptionRepository.save(subscription);

        return new SubscriptionResponseDTO(
                saved.getSubscriptionId(),
                saved.getSubscriptionName(),
                saved.getPrice(),
                saved.getDurationDays()
        );
    }

    @Override
    public List<SubscriptionResponseDTO> findSubscriptionsByUserId(Long userId) {
        return userSubscriptionRepository.findByUserId(userId).stream()
                .map(userSub -> {
                    Subscription sub = userSub.getSubscription();
                    return new SubscriptionResponseDTO(
                            sub.getSubscriptionId(),
                            sub.getSubscriptionName(),
                            sub.getPrice(),
                            sub.getDurationDays()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }
}
