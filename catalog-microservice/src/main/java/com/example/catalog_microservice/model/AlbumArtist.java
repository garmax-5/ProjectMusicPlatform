package com.example.catalog_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "album_artists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArtist {

    @EmbeddedId
    private AlbumArtistId id;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AlbumArtistId implements Serializable {
        private Long albumId;
        private Long artistId;
    }
}

