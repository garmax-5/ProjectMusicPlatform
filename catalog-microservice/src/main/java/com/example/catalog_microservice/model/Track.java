package com.example.catalog_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tracks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    @Getter
    private Long id;

    @Column(name = "track_name", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "duration", nullable = false)
    @Getter @Setter
    private Integer duration;

    @Column(name = "file_url", nullable = false)
    @Getter @Setter
    private String fileUrl;

    @Column(name = "album_id")
    @Getter @Setter
    private Long albumId;

    @Column(name = "genre_id")
    @Getter @Setter
    private Long genreId;

    @Column(name = "media_type_id", nullable = false)
    @Getter @Setter
    private Long mediaTypeId;
}

