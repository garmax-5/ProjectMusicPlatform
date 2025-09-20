package com.example.catalog_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDisplayDTO {
    private Long id;
    private String name;
    private String artistName;
    private String fileUrl;
}

