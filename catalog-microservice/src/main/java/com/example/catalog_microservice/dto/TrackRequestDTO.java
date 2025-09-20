package com.example.catalog_microservice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TrackRequestDTO {

    @NotBlank(message = "Название трека не может быть пустым")
    private String name;

    @NotNull(message = "Длительность трека обязательна")
    @Min(value = 1, message = "Длительность должна быть положительной")
    private Integer duration;

    @NotBlank(message = "URL файла обязателен")
    private String fileUrl;

    private Long albumId;
    private Long genreId;

    @NotNull(message = "mediaTypeId обязателен")
    private Long mediaTypeId;
}

