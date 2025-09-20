package com.example.catalog_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistProfileDTO {
    private Long id;
    private String name;
    private Integer listenerCount;
    private List<SimpleTrackDTO> tracks;
    private List<SimpleAlbumDTO> albums;
}
