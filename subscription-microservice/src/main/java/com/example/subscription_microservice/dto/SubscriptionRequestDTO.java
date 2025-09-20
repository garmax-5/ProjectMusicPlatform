package com.example.subscription_microservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SubscriptionRequestDTO {

    @NotBlank(message = "Название подписки обязательно")
    private String subscriptionName;

    @NotNull(message = "Цена обязательна")
    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Double price;

    @NotNull(message = "Длительность обязательна")
    @Min(value = 1, message = "Минимальная длительность — 1 день")
    private Integer durationDays;
}
