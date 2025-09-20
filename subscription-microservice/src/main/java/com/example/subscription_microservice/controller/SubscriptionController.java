package com.example.subscription_microservice.controller;

import com.example.subscription_microservice.dto.SubscriptionRequestDTO;
import com.example.subscription_microservice.dto.SubscriptionResponseDTO;
import com.example.subscription_microservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> createSubscription(@RequestBody @Valid SubscriptionRequestDTO request) {
        SubscriptionResponseDTO response = subscriptionService.createSubscription(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<SubscriptionResponseDTO>> getUserSubscriptions(@PathVariable Long userId) {
        List<SubscriptionResponseDTO> subscriptions = subscriptionService.findSubscriptionsByUserId(userId);

        if (subscriptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(subscriptions);
    }
}
