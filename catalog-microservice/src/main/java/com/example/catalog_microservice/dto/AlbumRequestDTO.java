package com.example.catalog_microservice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AlbumRequestDTO {

    @NotBlank(message = "Название альбома обязательно")
    private String albumName;

    @NotNull(message = "Год релиза обязателен")
    @Min(value = 1800, message = "Год релиза должен быть >= 1800")
    private Integer releaseYear;

    @NotNull(message = "Тип альбома обязателен")
    private Long albumTypeId;

    private String coverImage;
}
