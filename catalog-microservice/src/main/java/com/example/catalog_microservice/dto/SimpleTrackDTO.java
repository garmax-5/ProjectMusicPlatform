package com.example.catalog_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTrackDTO {
    private Long id;
    private String name;
    private String coverImage;
}
