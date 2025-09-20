package com.example.catalog_microservice.repository;

import com.example.catalog_microservice.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByNameContainingIgnoreCase(String name);

    List<Track> findByAlbumId(Long albumId);

    List<Track> findByGenreIdIn(List<Long> genreIds);
}
