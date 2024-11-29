package com.example.subscription_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    @Getter
    private Long subscriptionId;

    @Column(name = "subscription_name", nullable = false, length = 50)
    @Getter @Setter
    private String subscriptionName;

    @Column(name = "price", nullable = false, precision = 7, scale = 2)
    @Getter @Setter
    private Double price;

    @Column(name = "duration_days", nullable = false)
    @Getter @Setter
    private Integer durationDays;
}

