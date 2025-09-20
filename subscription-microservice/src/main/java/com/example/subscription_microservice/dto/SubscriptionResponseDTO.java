package com.example.subscription_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscriptionResponseDTO {
    private Long subscriptionId;
    private String subscriptionName;
    private Double price;
    private Integer durationDays;
}
