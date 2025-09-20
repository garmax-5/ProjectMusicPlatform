package com.example.user_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackResponseDTO {
    private Long id;
    private String name;
    private Integer duration;
    private String artistName;
    private String fileUrl;
    private Long albumId;
    private Long genreId;
    private Long mediaTypeId;
}
