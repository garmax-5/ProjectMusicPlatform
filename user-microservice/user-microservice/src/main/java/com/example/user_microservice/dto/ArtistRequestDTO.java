package com.example.user_microservice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ArtistRequestDTO {

    @NotBlank(message = "Имя исполнителя обязательно")
    private String name;

    @Min(value = 0, message = "Количество слушателей не может быть отрицательным")
    private Integer listenerCount = 0;
}
