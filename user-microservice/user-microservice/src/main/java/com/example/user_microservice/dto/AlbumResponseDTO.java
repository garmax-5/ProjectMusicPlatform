package com.example.user_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumResponseDTO {
    private Long id;
    private String albumName;
    private Integer releaseYear;
    private Long albumTypeId;
    private String coverImage;
}
