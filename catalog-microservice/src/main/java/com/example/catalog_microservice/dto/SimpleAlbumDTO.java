package com.example.catalog_microservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAlbumDTO {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String coverImage;
}

