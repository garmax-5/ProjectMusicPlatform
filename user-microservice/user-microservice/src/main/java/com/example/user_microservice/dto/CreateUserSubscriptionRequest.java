package com.example.user_microservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserSubscriptionRequest {
    private Long userId;
    private Long subscriptionId;
}
