package com.example.catalog_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "albums")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    @Column(name = "album_name", nullable = false)
    private String albumName;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name = "album_type_id", nullable = false)
    private Long albumTypeId;

    @Column(name = "cover_image")
    private String coverImage;
}
