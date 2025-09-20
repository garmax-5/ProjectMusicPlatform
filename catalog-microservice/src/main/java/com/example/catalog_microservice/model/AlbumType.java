package com.example.catalog_microservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "album_types")
@Getter
@Setter
@NoArgsConstructor
public class AlbumType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_type_id")
    private Long id;

    @Column(name = "album_type_name")
    private String name;
}
