package com.example.catalog_microservice.dto;

import com.example.catalog_microservice.dto.TrackDisplayDTO;
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
