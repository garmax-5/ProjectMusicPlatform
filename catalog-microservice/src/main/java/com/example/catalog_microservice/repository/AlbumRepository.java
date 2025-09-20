package com.example.catalog_microservice.repository;

import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.model.AlbumArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByAlbumNameContainingIgnoreCase(String name);
}
