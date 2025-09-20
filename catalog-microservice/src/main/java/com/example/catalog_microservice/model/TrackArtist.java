package com.example.catalog_microservice.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "track_artists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackArtist {

    @EmbeddedId
    private TrackArtistId id;

    @ManyToOne
    @MapsId("trackId")
    @JoinColumn(name = "track_id")
    private Track track;

    @ManyToOne
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TrackArtistId implements Serializable {
        private Long trackId;
        private Long artistId;
    }
}



