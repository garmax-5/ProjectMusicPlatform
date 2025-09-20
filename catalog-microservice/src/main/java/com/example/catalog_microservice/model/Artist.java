package com.example.catalog_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "artists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @Column(name = "artist_name", nullable = false)
    private String name;

    @Column(name = "listener_count", nullable = false)
    private Integer listenerCount = 0;
}