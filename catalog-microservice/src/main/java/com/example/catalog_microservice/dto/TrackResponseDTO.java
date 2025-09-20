package com.example.catalog_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrackResponseDTO {
    private Long id;
    private String name;
    private Integer duration; // в секундах
    private String artistName;
    private String fileUrl;
    private Long albumId;
    private Long genreId;
    private Long mediaTypeId;
}
