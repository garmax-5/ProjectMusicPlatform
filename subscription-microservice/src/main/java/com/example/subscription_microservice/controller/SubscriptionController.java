package com.example.subscription_microservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.subscription_microservice.dto.SubscriptionRequest;
import com.example.subscription_microservice.model.Subscription;
import com.example.subscription_microservice.model.UserSubscription;
import com.example.subscription_microservice.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable Long userId) {
        List<Subscription> subscriptions = subscriptionService.findSubscriptionsByUserId(userId);
        return ResponseEntity.ok(subscriptions);
    }


//    @PostMapping("/subscribe")
//    public ResponseEntity<?> subscribe(@RequestHeader("Authorization") String token,
//                                       @RequestParam Long subscriptionId) {
//        if (!token.startsWith("Bearer ")) {
//            return ResponseEntity.badRequest().body("Недопустимый формат токена");
//        }
//
//        try {
//            String jwtToken = token.substring(7); // Убираем "Bearer " из заголовка
//            UserSubscription userSubscription = subscriptionService.addSubscriptionToUser(jwtToken, subscriptionId);
//            return ResponseEntity.ok(userSubscription);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

}

