package com.example.user_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDisplayDTO {
    private Long id;
    private String title;
    private String coverImage;
    private Integer releaseYear;
    private String artistName;
    private List<TrackDisplayDTO> tracks;
}


