package com.example.subscription_microservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.subscription_microservice.model.Subscription;
//import com.example.subscription_microservice.model.User;
import com.example.subscription_microservice.model.UserSubscription;
import com.example.subscription_microservice.repository.SubscriptionRepository;
import com.example.subscription_microservice.repository.UserSubscriptionRepository;
//import com.example.subscription_microservice.security.JwtTokenUtil;


//import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
//    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                               UserSubscriptionRepository userSubscriptionRepository /*,JwtTokenUtil jwtTokenUtil */) {
        this.subscriptionRepository = subscriptionRepository;
        this.userSubscriptionRepository = userSubscriptionRepository;
//        this.jwtTokenUtil=jwtTokenUtil;
    }


    //    @Transactional
////    @Scheduled(cron = "0 30 11 * * ?")
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void deactivateExpiredSubscriptions() {
//        LocalDate currentDate = LocalDate.now();
//        userSubscriptionRepository.deactivateExpiredSubscriptions(currentDate);
//        log.info("Подписки с истекшим сроком действия деактивированы на:{}", currentDate);
//    }


    public UserSubscription addSubscriptionToUser(Long userId, Long subscriptionId) {
        // Проверяем существование подписки
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Подписка с ID " + subscriptionId + " не найдена"));

        // Создаем запись о подписке пользователя
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(subscription.getDurationDays());

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUserId(userId); // Используем userId напрямую
        userSubscription.setSubscription(subscription);
        userSubscription.setSubscriptionStartDate(startDate);
        userSubscription.setSubscriptionEndDate(endDate);
        userSubscription.setIsActive(true);

        // Сохраняем связь в таблице user_subscriptions
        return userSubscriptionRepository.save(userSubscription);
    }


    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> findSubscriptionsByUserId(Long userId) {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findByUserId(userId);
        return userSubscriptions.stream()
                .map(UserSubscription::getSubscription)
                .collect(Collectors.toList());
    }

}

