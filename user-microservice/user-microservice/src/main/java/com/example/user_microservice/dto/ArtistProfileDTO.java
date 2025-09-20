package com.example.user_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistProfileDTO {
    private String name;
    private Integer listenerCount;
    private List<TrackWithCoverDTO> tracks;
    private List<SimpleAlbumDTO> albums;
}